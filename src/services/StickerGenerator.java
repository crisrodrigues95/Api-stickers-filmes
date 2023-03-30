package services;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.imageio.ImageIO;



public class StickerGenerator {


    

    

    public void creating(InputStream inputStream, String filename, String title) throws Exception{

        //definicao de frase e reação

        

        String word = title;
        InputStream reactionImage;

        reactionImage = new FileInputStream(new File("imagens/eu.jpeg"));
        

       

        // ler a imagem
        
        BufferedImage originalImage = ImageIO.read(inputStream);

        // criar nova imagem

        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        int newHeight = height+ 200;
        BufferedImage newImage = new BufferedImage(width, newHeight, BufferedImage.TRANSLUCENT);

        // copiar imagem antiga para nova

        Graphics2D graphics = (Graphics2D) newImage.getGraphics();
        graphics.drawImage(originalImage, 0, 0, null);

        //Colocar a imagem de sobreposição

        BufferedImage coverImage = ImageIO.read(reactionImage);
        int coverImagePositionY = newHeight - coverImage.getHeight();
        graphics.drawImage(coverImage, 0, coverImagePositionY, null);

       

        //configurar fonte
        var font = new Font("Impact", Font.BOLD, 60 );
        graphics.setFont(font);
        graphics.setColor(Color.yellow);

        

        // escrever uma frase na nova imagem

       


        
        FontMetrics fontMetrics = graphics.getFontMetrics();
        Rectangle2D rectangle = fontMetrics.getStringBounds(word, graphics);
        int wordWidth = (int)rectangle.getWidth();
        int wordPositionX = (width - wordWidth)/2;
        int wordPositionY = (newHeight-100);

        graphics.drawString(word, wordPositionX, wordPositionY);

        FontRenderContext fontRenderContext = graphics.getFontRenderContext();
        var textLayout = new TextLayout(word, font, fontRenderContext);
        Shape outline = textLayout.getOutline(null);
        AffineTransform transform= graphics.getTransform();
        transform.translate(wordPositionX, wordPositionY);
        graphics.setTransform(transform);
        var outlineStroke = new BasicStroke(width * 0.004f);
        graphics.setStroke(outlineStroke);
        graphics.setColor(Color.BLACK);
        graphics.draw(outline);
        graphics.setClip(outline);

       

        // escrever a nova imagem em um arquivo
        Files.createDirectories(Paths.get("output"));
        ImageIO.write(newImage, "png", new File("output/"+filename));

    }

   
    
    
}
