package com;

import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, UnsupportedFlavorException, InterruptedException {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        Image image=new BufferedImage(100,100, BufferedImage.TYPE_INT_ARGB);
        Graphics g=image.getGraphics();
        g.fillOval(0,0,100,100);

        ImageTransferable it = new ImageTransferable(image);
        clipboard.setContents(it, null);

        Thread.sleep(3000);

        DataFlavor flavor = DataFlavor.imageFlavor;
        clipboard.getData(flavor);
        if (clipboard.isDataFlavorAvailable(flavor)) {
            Image img=(Image) clipboard.getData(flavor);
        }
    }
}

class ImageTransferable implements Transferable {
    private Image theImage;

    public ImageTransferable(Image image) {
        theImage = image;
    }
    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{DataFlavor.imageFlavor};
    }

    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(DataFlavor.imageFlavor);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        if (flavor.equals(DataFlavor.imageFlavor)) {
            return theImage;
        } else {
            throw new UnsupportedFlavorException(flavor);
        }
    }
}