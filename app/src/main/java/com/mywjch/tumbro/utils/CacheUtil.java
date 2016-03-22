package com.mywjch.tumbro.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by mywjch on 16/3/14.
 */
public class CacheUtil {
    public static final String TAG = "CacheUtil";
    private static final int MB = 1024;
    private static final int CACHE_SIZE = 40;
    private static final int FREE_SD_SPACE_NEEDED_TO_CACHE = 100;
    private static final String WHOLESALE_CONV = "";
    private static final int mTimeDiff = 300;
    private static final int HARD_CACHE_CAPACITY = 400;
    /**
     * 当mHardBitmapCache的key大于30的时候，会根据LRU算法把最近没有被使用的key放入到这个缓存中。
     * Bitmap使用了SoftReference，当内存空间不足时，此cache中的bitmap会被垃圾回收掉
     */
    private final static ConcurrentHashMap<String, SoftReference<Bitmap>> mSoftBitmapCache =
            new ConcurrentHashMap<String, SoftReference<Bitmap>>(HARD_CACHE_CAPACITY / 2);
    private final static HashMap<String, Bitmap> mHardBitmapCache = new LinkedHashMap<String, Bitmap>(HARD_CACHE_CAPACITY / 2, 0.75f, true) {
        @Override
        protected boolean removeEldestEntry(LinkedHashMap.Entry<String, Bitmap> eldest) {
            if (size() > HARD_CACHE_CAPACITY) {
                //当map的size大于30时，把最近不常用的key放到mSoftBitmapCache中，从而保证mHardBitmapCache的效率
                mSoftBitmapCache.put(eldest.getKey(), new SoftReference<Bitmap>(eldest.getValue()));
                return true;
            } else
                return false;
        }
    };

    private Bitmap getBitmapFromCache(String imageUrl) {

        synchronized (mHardBitmapCache) {
            final Bitmap bitmap = mHardBitmapCache.get(imageUrl);
            if (bitmap != null) {
                //如果找到的话，把元素移到linkedhashmap的最前面，从而保证在LRU算法中是最后被删除
                mHardBitmapCache.remove(imageUrl);
                mHardBitmapCache.put(imageUrl, bitmap);
                return bitmap;
            }
        }

        SoftReference<Bitmap> softReference = mSoftBitmapCache.get(imageUrl);
        if (softReference != null) {
            final Bitmap bitmap = softReference.get();
            if (bitmap != null) {
                return bitmap;
            } else {
                mSoftBitmapCache.remove(imageUrl);
            }
        }
        return null;
    }

    /**
     * 计算存储目录下的文件大小，当文件总大小大于规定的CACHE_SIZE或者sdcard剩余空间小于FREE_SD_SPACE_NEEDED_TO_CACHE的规定
     * 那么删除40%最近没有被使用的文件
     *
     * @param dirPath
     */
    private void removeCache(String dirPath) {
        File dir = new File(dirPath);
        File[] files = dir.listFiles();
        if (files == null) {
            return;
        }
        int dirSize = 0;
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().contains(WHOLESALE_CONV)) {
                dirSize += files[i].length();
            }
        }
        if (dirSize > CACHE_SIZE * MB || FREE_SD_SPACE_NEEDED_TO_CACHE > freeSpaceOnSd()) {
            int removeFactor = (int) ((0.4 * files.length) + 1);

            Arrays.sort(files, new FileLastModifSort());

            Log.i(TAG, "Clear some expiredcache files ");

            for (int i = 0; i < removeFactor; i++) {

                if (files[i].getName().contains(WHOLESALE_CONV)) {

                    files[i].delete();

                }

            }

        }

    }

    /**
     * 删除过期文件
     *
     * @param dirPath  文件路径
     * @param filename 文件名
     */
    private void removeExpiredCache(String dirPath, String filename) {

        File file = new File(dirPath, filename);

        if (System.currentTimeMillis() - file.lastModified() > mTimeDiff) {

            Log.i(TAG, "Clear some expiredcache files ");

            file.delete();

        }

    }

    /**
     * 计算sdcard上的剩余空间
     *
     * @return
     */
    private int freeSpaceOnSd() {
        StatFs stat = new StatFs(Environment.getExternalStorageDirectory().getPath());
        double sdFreeMB = ((double) stat.getAvailableBlocks() * (double) stat.getBlockSize()) / MB;
        return (int) sdFreeMB;
    }

    /**
     * 修改文件的最后修改时间
     *
     * @param dir
     * @param fileName
     */
    private void updateFileTime(String dir, String fileName) {
        File file = new File(dir, fileName);
        long newModifiedTime = System.currentTimeMillis();
        file.setLastModified(newModifiedTime);
    }

    public void setCache() {


    }

    /**
     * TODO 根据文件的最后修改时间进行排序 *
     */
    class FileLastModifSort implements Comparator<File> {
        public int compare(File arg0, File arg1) {
            if (arg0.lastModified() > arg1.lastModified()) {
                return 1;
            } else if (arg0.lastModified() == arg1.lastModified()) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}
