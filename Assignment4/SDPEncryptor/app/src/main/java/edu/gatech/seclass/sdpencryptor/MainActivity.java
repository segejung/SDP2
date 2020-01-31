package edu.gatech.seclass.sdpencryptor;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.HashMap;
import java.util.Random;


public final class MainActivity extends AppCompatActivity {
    private HashMap _$_findViewCache;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(-1300009);
        Button rollButton = (Button)this.findViewById(-1000121);
        final TextView resultsTextView = (TextView)this.findViewById(-1000026);
        final SeekBar seekBar = (SeekBar)this.findViewById(-1000161);
        rollButton.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                Default var10000 = Random.Default;
                SeekBar var10001 = seekBar;
                Intrinsics.checkExpressionValueIsNotNull(var10001, "seekBar");
                int rand = var10000.nextInt(var10001.getProgress()) + 1;
                TextView var3 = resultsTextView;
                Intrinsics.checkExpressionValueIsNotNull(var3, "resultsTextView");
                var3.setText((CharSequence)String.valueOf(rand));
            }
        }));
    }

    public View _$_findCachedViewById(int var1) {
        if (this._$_findViewCache == null) {
            this._$_findViewCache = new HashMap();
        }

        View var2 = (View)this._$_findViewCache.get(var1);
        if (var2 == null) {
            var2 = this.findViewById(var1);
            this._$_findViewCache.put(var1, var2);
        }

        return var2;
    }

    public void _$_clearFindViewByIdCache() {
        if (this._$_findViewCache != null) {
            this._$_findViewCache.clear();
        }

    }
}
