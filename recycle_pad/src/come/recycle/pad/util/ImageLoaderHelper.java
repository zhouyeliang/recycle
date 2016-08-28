package come.recycle.pad.util;

import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.widget.ImageView;

import com.example.recycle_pad.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;

/**
 * ʹ��universalimageloader��װ��ͼƬ�����࣬���Լ��ظ��಻ͬurl��ͼƬ�����ܹ����imageview��С�Զ�����ͼƬ�Ĵ�С
 * 
 * @author zhouyeliang
 * @Date 2015-11-3
 */
public class ImageLoaderHelper {

	public static ImageLoader imageLoader = ImageLoader.getInstance();

	/**
	 * ͼƬ��ʽ��Ż���
	 */
	static Map<DisplayOptionType, DisplayImageOptions> displayOptionMap = new HashMap<ImageLoaderHelper.DisplayOptionType, DisplayImageOptions>();

	/**
	 * ��ʼ��ͼƬ������������
	 * 
	 * @param context
	 */
	public static void initImageLoader(Context context) {
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context).memoryCacheExtraOptions(180, 180)
				.threadPriority(Thread.NORM_PRIORITY - 1)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024)) // �����ͼƬ���������޶�ֵ����ɾ��ʹ��Ƶ����С��bitmap
				.memoryCacheSize(2 * 1024 * 1024) // �����ڴ滺��Ϊ2M
				.discCacheSize(50 * 1024 * 1024) // ����Ӳ�̻���Ĵ�СΪ50M
				.discCacheFileCount(100)
				// .writeDebugLogs() // Remove for release app
				.build();
		imageLoader.init(config);

	}

	/**
	 * ��ȡͼƬ����ʱͼƬ��ʽ��������
	 * 
	 * @param type
	 *            ͼƬ��ʽ����
	 * @return
	 */
	public static DisplayImageOptions getDisplayOpts(DisplayOptionType type) {
		DisplayImageOptions dio;
		if (null == (dio = displayOptionMap.get(type))) {

			switch (type) {
			case Round:
				dio = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_launcher)
						.showImageForEmptyUri(R.drawable.ic_launcher)
						.showImageOnFail(R.drawable.ic_launcher)
						.displayer(new RoundedBitmapDisplayer(15))
						.cacheInMemory(true).cacheOnDisc(true).build();
				break;

			case LARGE:
				dio = new DisplayImageOptions.Builder()
						.showImageOnLoading(android.R.drawable.ic_menu_gallery)
						.showImageForEmptyUri(R.drawable.ic_launcher)
						.showImageOnFail(R.drawable.ic_launcher)
						.displayer(new SimpleBitmapDisplayer())
						.cacheInMemory(true).cacheOnDisc(true).build();
				break;

			case NO_CACHE:
				dio = new DisplayImageOptions.Builder()
						.showImageOnLoading(android.R.drawable.ic_menu_gallery)
						.showImageForEmptyUri(R.drawable.ic_launcher)
						.showImageOnFail(R.drawable.ic_launcher)
						.displayer(new SimpleBitmapDisplayer())
						.cacheInMemory(false).cacheOnDisc(false).build();
				break;

			default:
				dio = new DisplayImageOptions.Builder()
						.showImageOnLoading(R.drawable.ic_launcher)
						.showImageForEmptyUri(R.drawable.ic_launcher)
						.showImageOnFail(R.drawable.ic_launcher)
						.displayer(new SimpleBitmapDisplayer())
						.cacheInMemory(true).cacheOnDisc(true).build();
				break;
			}
			displayOptionMap.put(type, dio);
		}
		return dio;
	}

	/**
	 * ���������ϵ�ͼƬ��Դ
	 */
	public static void displayImageFromURL(String imageUrl, ImageView imgView) {
		imageLoader.displayImage(imageUrl, imgView,
				getDisplayOpts(DisplayOptionType.DEFAULT));
	}

	public static void displayImageFromURL(String imageUrl, ImageView imgView,
			DisplayOptionType type) {
		imageLoader.displayImage(imageUrl, imgView, getDisplayOpts(type));
	}

	/**
	 * �����ļ�ϵͳ�е�ͼƬ��Դ
	 */
	public static void displayImageFromFile(String fileUrl, ImageView imgView) {
		String imageUrl = Scheme.FILE.wrap(fileUrl);
		imageLoader.displayImage(imageUrl, imgView,
				getDisplayOpts(DisplayOptionType.DEFAULT));
	}

	public static void displayImageFromFile(String fileUrl, ImageView imgView,
			DisplayOptionType type) {
		String imageUrl = Scheme.FILE.wrap(fileUrl);
		imageLoader.displayImage(imageUrl, imgView, getDisplayOpts(type));
	}

	/**
	 * ����asset�ļ����е�ͼƬ��Դ
	 */
	public static void displayImageFromAsset(String assetUrl, ImageView imgView) {
		String imageUrl = Scheme.ASSETS.wrap(assetUrl);
		imageLoader.displayImage(imageUrl, imgView,
				getDisplayOpts(DisplayOptionType.DEFAULT));
	}

	public static void displayImageFromAsset(String assetUrl,
			ImageView imgView, DisplayOptionType type) {
		String imageUrl = Scheme.ASSETS.wrap(assetUrl);
		imageLoader.displayImage(imageUrl, imgView, getDisplayOpts(type));
	}

	/**
	 * ����contentprivider�е�ͼƬ��Դ
	 */
	public static void displayImageFromContentprivider(String contentUrl,
			ImageView imgView) {
		imageLoader.displayImage(contentUrl, imgView,
				getDisplayOpts(DisplayOptionType.DEFAULT));
	}

	public static void displayImageFromContentprivider(String contentUrl,
			ImageView imgView, DisplayOptionType type) {
		imageLoader.displayImage(contentUrl, imgView, getDisplayOpts(type));
	}

	/**
	 * ����Drawable�е�ͼƬ��Դ
	 */
	public static void displayImageFromDrawable(String drawableUrl,
			ImageView imgView) {
		String imageUrl = Scheme.DRAWABLE.wrap(drawableUrl);
		imageLoader.displayImage(imageUrl, imgView,
				getDisplayOpts(DisplayOptionType.DEFAULT));
	}

	public static void displayImageFromDrawable(String drawableUrl,
			ImageView imgView, DisplayOptionType type) {
		String imageUrl = Scheme.DRAWABLE.wrap(drawableUrl);
		imageLoader.displayImage(imageUrl, imgView, getDisplayOpts(type));
	}

	public enum DisplayOptionType {
		DEFAULT, LARGE, NO_CACHE, Round
	}
}
