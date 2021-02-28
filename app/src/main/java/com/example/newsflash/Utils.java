package com.example.newsflash;



public class Utils {

    public static String replace(String articleId){

        String newArticleId =  articleId.replaceAll("/", "-");
         String replacedId = newArticleId.replace("https:--", "");
       String newId = replacedId.substring(0,replacedId.length() - 1);


        return newId;
    }

//    public static boolean checkInternetConnection(Context context) {
//        ConnectivityManager connectivity = (ConnectivityManager) context
//                .getSystemService(Context.CONNECTIVITY_SERVICE);
//        if (connectivity == null) {
//            return false;
//        } else {
//            NetworkInfo[] info = connectivity.getAllNetworkInfo();
//            if (info != null) {
//                for (int i = 0; i < info.length; i++) {
//                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
//                        return true;
//                    }
//                }
//            }
//        }
//        return false;
//    }
}
