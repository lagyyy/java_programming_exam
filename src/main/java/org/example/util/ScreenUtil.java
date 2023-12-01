package org.example.util;

import javax.swing.*;
import java.awt.*;

public class ScreenUtil {

    public static void setCenter(JFrame jFrame,int windowsWidth,int windowsHeight){
         int width = Toolkit.getDefaultToolkit().getScreenSize().width;
         int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        // 定义窗体的宽高
//         int windowsWedth = 500;
//         int windowsHeight = 500;
        jFrame.setBounds((width - windowsWidth) / 2,
                (height - windowsHeight) / 2, windowsWidth, windowsHeight);
    }
}
