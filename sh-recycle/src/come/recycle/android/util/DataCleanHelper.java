package come.recycle.android.util;

import java.io.File;
import java.math.BigDecimal;

import android.content.Context;
import android.os.Environment;

public class DataCleanHelper {
    
    public static void clearAllCache(Context context) {
        deleteDir(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { 
            deleteDir(context.getExternalCacheDir());
        } 
    }
    
    private static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }
    
    /**
     * ��ȡ�ܵĻ����С
     * @param context
     * @return
     * @throws Exception
     */
    public static String getTotalCacheSize(Context context) throws Exception {
        long cacheSize = getFolderSize(context.getCacheDir());
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { 
            cacheSize += getFolderSize(context.getExternalCacheDir());
        } 
        return getFormatSize(cacheSize);
    }
    
    public static long getFolderSize(File file) throws Exception { 
        long size = 0; 
        try { 
            File[] fileList = file.listFiles(); 
            for (int i = 0; i < fileList.length; i++) { 
                // ������滹���ļ� 
                if (fileList[i].isDirectory()) { 
                    size = size + getFolderSize(fileList[i]); 
                } else { 
                    size = size + fileList[i].length(); 
                } 
            } 
        } catch (Exception e) { 
            e.printStackTrace(); 
        } 
        return size; 
    }
    
    /**
     * ��ʽ����λ
     * @param size
     */
    public static String getFormatSize(double size) { 
        double kiloByte = size / 1024; 
        if (kiloByte < 1) { 
           return size + "B"; 
        } 
   
        double megaByte = kiloByte / 1024; 
        if (megaByte < 1) { 
            BigDecimal result1 = new BigDecimal(Double.toString(kiloByte)); 
            return result1.setScale(2, BigDecimal.ROUND_HALF_UP) 
                    .toPlainString() + "K"; 
        } 
   
        double gigaByte = megaByte / 1024; 
        if (gigaByte < 1) { 
            BigDecimal result2 = new BigDecimal(Double.toString(megaByte)); 
            return result2.setScale(2, BigDecimal.ROUND_HALF_UP) 
                    .toPlainString() + "M"; 
        } 
   
        double teraBytes = gigaByte / 1024; 
        if (teraBytes < 1) { 
            BigDecimal result3 = new BigDecimal(Double.toString(gigaByte)); 
            return result3.setScale(2, BigDecimal.ROUND_HALF_UP) 
                    .toPlainString() + "G"; 
        } 
        BigDecimal result4 = new BigDecimal(teraBytes); 
        return result4.setScale(2, BigDecimal.ROUND_HALF_UP).toPlainString() 
                + "T"; 
    }
}