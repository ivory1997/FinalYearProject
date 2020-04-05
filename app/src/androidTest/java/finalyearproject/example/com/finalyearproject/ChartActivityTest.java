package finalyearproject.example.com.finalyearproject;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ChartActivityTest {


    @Before
    public void setUp() throws
            Exception {

        chart = new ChartActivity();
    }

    private ChartActivity chart;

    @Test
    public void validate_profilePic_returnsTrue() throws
            Exception {

        Assert.assertFalse(chart.isProfilePicSet(""));
    }


}