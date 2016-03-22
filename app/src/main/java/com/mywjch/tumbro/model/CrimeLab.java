package com.mywjch.tumbro.model;

import android.content.Context;
import android.util.Log;

import com.mywjch.tumbro.utils.JJsonSerializel;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by mywjch on 15/8/11.
 */
public class CrimeLab {
    private static final String TAG = "CrimeLab";
    private static final String FILENAME = "crimes.json";
    private static CrimeLab crimeLab;
    private JJsonSerializel mJsonSerializel;
    private Context mAppContext;
    private ArrayList<Crime> mCrimes;

    private CrimeLab(Context appContext) {
        mAppContext = appContext;
        mJsonSerializel = new JJsonSerializel(mAppContext, FILENAME);
        try {
            mCrimes = mJsonSerializel.loadCrimes();
            Log.e(TAG, "CrimeLab 加载crimes" + mCrimes.size());
        } catch (IOException e) {
            mCrimes = new ArrayList<Crime>();
            Log.e(TAG, "CrimeLab 加载crimes出错");
        }
    }

    public static CrimeLab get(Context c) {
        if (crimeLab == null) {
            crimeLab = new CrimeLab(c.getApplicationContext());
        }
        return crimeLab;
    }

    public ArrayList<Crime> getCrimes() {
        Log.e("mywjch", "mCrimes.size()===" + mCrimes.size() + "");
        return mCrimes;
    }

    public Crime getCrime(UUID id) {
        for (Crime c : mCrimes) {
            if (c.getmId().equals(id)) {
                return c;
            }
        }
        return null;
    }

    public void add(Crime crime) {
        mCrimes.add(crime);
    }

    public void del(Crime crime) {
        mCrimes.remove(crime);
    }

    public boolean saveCrimes() {
        try {
            mJsonSerializel.saveCrime(mCrimes);
            Log.e(TAG, "保存进了json");
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
