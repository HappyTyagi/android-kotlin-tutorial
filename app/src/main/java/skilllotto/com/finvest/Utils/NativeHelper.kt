package skilllotto.com.finvest.Utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.provider.Settings

import java.net.InetAddress


object NativeHelper {
    //You can replace it with your name
    val isInternetAvailable: Boolean
        get() {
            try {
                val ipAddr = InetAddress.getByName("google.com")
                return ipAddr != ""

            } catch (e: Exception) {
                return false
            }

        }

    //Build.VERSION.RELEASE
    val myDeviceVersion: String
        get() = "" + java.lang.Double.valueOf(Build.VERSION.RELEASE)!!

    /**
     * @author vishwa
     * function is used to detect
     * network is available or not
     * @param context
     * @return
     */
    fun isConnectionAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo ?: return false

        return networkInfo != null && networkInfo.isConnectedOrConnecting
    }
    /**
     *
     * @param context
     * @param number
     */
    /*	public static void makeCall(Context context, String number)
	{
		Intent call		=new Intent(Intent.ACTION_CALL);
		call.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		call.setData(Uri.parse("tel:"+number));
		context.startActivity(call);
	}
	*/
    /**
     * check is there camera exists or not in device
     * @param context
     * @return
     *//*
	public static  boolean isCameraAvailable(Context context) {
		PackageManager packageManager=context.getPackageManager();
	    if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
	        // this device has a camera
	        return true;
	    } else {
	        // no camera on this device
	        return false;
	    }
	}

	@SuppressLint("NewApi")
	public static boolean isCameraAvailbles(Context context)
	{
		int numCameras = Camera.getNumberOfCameras();
		if (numCameras > 1) {
			return true;
		}
		return false;

	}

	public static boolean isCameraAvailableInDevice(Context context)
	{
		PackageManager pm=context.getPackageManager();
		boolean frontCam, rearCam;
		//Must have a targetSdk >= 9 defined in the AndroidManifest
		frontCam 	= pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
		rearCam 	= pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
		if(!frontCam && !rearCam)
		{
			return false;
		}
		return true;
	}

	*/
    /**
     * check network Location enable or not
     *//*
	public static  boolean isGpsEnable(Context context)
	{
		LocationManager lm = (LocationManager)context. getSystemService(Context.LOCATION_SERVICE);
		//if(Build.VERSION>VERSION.SDK_INT
		if(lm.isProviderEnabled(LocationManager.GPS_PROVIDER))
		{
			return true;
		}
		return false;
	}
	public static boolean isNetworkProviderEnable(Context context)
	{
		LocationManager lm = (LocationManager)context. getSystemService(Context.LOCATION_SERVICE);
		if(lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER))
		{
			return true;
		}
		return false;
	}
	public boolean isLocationProviderEnable(final Context context)
	{
		if(!isGpsEnable(context) || !isNetworkProviderEnable(context)) {
			  // Build the alert dialog
			  AlertDialog.Builder builder = new AlertDialog.Builder(context);
			  builder.setTitle("Location Services Not Active");
			  builder.setMessage("Please enable Location Services and GPS");
			  builder.setPositiveButton("OK", new DialogInterface.OnClickListener()
			  {
			  public void onClick(DialogInterface dialogInterface, int i) {
			    // Show location settings when the user acknowledges the alert dialog
				  Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
				  context.startActivity(intent);
			    }
			  });
			  Dialog alertDialog = builder.create();
			  alertDialog.setCanceledOnTouchOutside(false);
			  alertDialog.show();
			  return false;
			}
		return true;
	}

	*/
    /**
     * calculate distance between Two Points
     *//*
	public static double CalculationByDistance(LatLng StartP, LatLng EndP)
	{

        int Radius=6371;//radius of earth in Km
        double lat1 = StartP.latitude;
        double lat2 = EndP.latitude;
        double lon1 = StartP.longitude;
        double lon2 = EndP.longitude;
        double dLat = Math.toRadians(lat2-lat1);
        double dLon = Math.toRadians(lon2-lon1);
        double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
        Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) *
        Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = 2 * Math.asin(Math.sqrt(a));
        double valueResult= Radius*c;
        double km=valueResult/1;
        DecimalFormat newFormat = new DecimalFormat("####");
        int kmInDec =  Integer.valueOf(newFormat.format(km));
        double meter=valueResult%1000;
        int  meterInDec= Integer.valueOf(newFormat.format(meter));
        Log.i("Radius Value",""+valueResult+"   KM  "+kmInDec+" Meter   "+meterInDec);

        return Radius * c;

     }
		public static boolean  googleplaystatus(Context context)
		{
			int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
			// Showing status
	        if(status!=ConnectionResult.SUCCESS){ // Google Play Services are not available

	        	return false;
	        }
			return true;
		}


      public static boolean isMyServiceRunning(Context context, Class<?> serviceClass) {
		    ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
		    for (RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
		        if (serviceClass.getName().equals(service.service.getClassName())) {
		            return true;
		        }
		    }
		    return false;
		}
      */
    /**
     *
     * @param context
     * @return
     * get my unique device ID
     */
    fun getMyDeviceID(context: Context): String {
        return Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID)
    }


}
