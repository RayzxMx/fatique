package id.ac.unib.fafiquedriving.ui.slideshow;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class SlideshowViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    public SlideshowViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Scan QRcode berikut : ");
        // Mendapatkan objek SharedPreferences
    }

    public LiveData<String> getText() {
        return mText;
    }

    public Bitmap generateQRCode(String qrCodeData) {
        try {
            BitMatrix bitMatrix = new QRCodeWriter().encode(qrCodeData, BarcodeFormat.QR_CODE, 250, 250);
            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bitmap.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }
            return bitmap;
        } catch (WriterException e) {
            e.printStackTrace();
        }
        return null;
    }
}