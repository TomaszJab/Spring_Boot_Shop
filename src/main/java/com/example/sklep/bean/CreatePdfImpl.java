package com.example.sklep.bean;

import com.example.sklep.entity.Product;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.border.Border;
import com.itextpdf.layout.border.DashedBorder;
import com.itextpdf.layout.border.SolidBorder;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CreatePdfImpl implements CreatePdf{
    private String filePathPdf;
    private float threeColumn;
    private float twoColumnInsideTable;
    private float twoColumnWidth;
    private float twoColumnWidthHeader[];
    private float threeColimnWidthProductTable[];
    private float fullWidthDocument[];
    private String dataDocument;

    private String customerAdresEmail;

    private String sellerAdres;
    private float widthTableDivider[];
    private PdfWriter pdfWriter;

    private Document document;
    private Paragraph paragraph;
    private Table insideHeaderTable;
    private Table tableDividerSolidBorder;
    private Table tableHeader;
    private Table tableSellerAndCustomer;

    private Paragraph productParagraph;

    private Table headerProductThreeColumnTable;

    private float totalSumPrice;

    private Table threeCololumnProductTable;

    private Table tableDividerDashedBorder;

    private Table totalSumProductTable;
    private static int shoppingNo;

    public CreatePdfImpl() {
        filePathPdf = "shop.pdf";
        threeColumn = 190f;
        twoColumnInsideTable = 285f;
        twoColumnWidth = twoColumnInsideTable +150f;
        twoColumnWidthHeader = new float[]{twoColumnWidth, twoColumnInsideTable};
        threeColimnWidthProductTable = new float[]{threeColumn, threeColumn, threeColumn};
        fullWidthDocument = new float[]{threeColumn * 3};
        Date actualDate = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        dataDocument = simpleDateFormat.format(actualDate);
        customerAdresEmail = "adres email";
        sellerAdres = "Gall Anonim \n al. Jerozolimskie 54 \n 00-019 Warszawa";
        widthTableDivider = new float[]{threeColumn + 125f, threeColumn * 2};
    }

    private void createPdf(){
        try {
            pdfWriter = new PdfWriter(filePathPdf);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        PdfDocument pdfDocument = new PdfDocument(pdfWriter);
        pdfDocument.setDefaultPageSize(PageSize.A4);

        document = new Document(pdfDocument);
    }

    private void header(){
        paragraph = new Paragraph("\n");

        tableHeader = new Table(twoColumnWidthHeader);
        tableHeader.addCell(new Cell().add("Shopping").setFontSize(20f).setBold().setBorder(Border.NO_BORDER));

        insideHeaderTable = new Table(new float[]{twoColumnInsideTable /2, twoColumnInsideTable /2});
        insideHeaderTable.addCell(new Cell().add("Shopping No.").setBold().setBorder(Border.NO_BORDER));
        shoppingNo++;
        insideHeaderTable.addCell(new Cell().add(String.valueOf(shoppingNo)).setBorder(Border.NO_BORDER));
        insideHeaderTable.addCell(new Cell().add("Shopping Date").setBold().setBorder(Border.NO_BORDER));
        insideHeaderTable.addCell(new Cell().add(dataDocument).setBorder(Border.NO_BORDER));
        tableHeader.addCell(new Cell().add(insideHeaderTable).setBorder(Border.NO_BORDER));
    }

    private void dividerSolidBorder(){
        Border solidBorder = new SolidBorder(Color.GRAY,2f);
        tableDividerSolidBorder = new Table(fullWidthDocument);
        tableDividerSolidBorder.setBorder(solidBorder);
    }

    private void addDataSellerAndCustomer(){
        Table insideTableSeller = new Table(new float[]{twoColumnInsideTable +20});
        insideTableSeller.addCell(new Cell().add("Seller")
                .setBold().setBorder(Border.NO_BORDER));
        insideTableSeller.addCell(new Cell()
                .add(sellerAdres)
                .setBorder(Border.NO_BORDER));

        tableSellerAndCustomer = new Table(twoColumnWidthHeader);
        tableSellerAndCustomer.addCell(new Cell()
                .add(insideTableSeller)
                .setBorder(Border.NO_BORDER));

        Table insideTableCustomer = new Table(new float[]{twoColumnInsideTable +20});
        insideTableCustomer.addCell(new Cell().add("Customer")
                .setBold().setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER));
        insideTableCustomer.addCell(new Cell()
                .add("Customer \n "+customerAdresEmail)
                .setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER));

        tableSellerAndCustomer.addCell(new Cell().add(insideTableCustomer).setBorder(Border.NO_BORDER));
    }

    private void headerProductTable(){
        productParagraph = new Paragraph("Products");
        productParagraph.setBold();

        headerProductThreeColumnTable = new Table(threeColimnWidthProductTable);
        headerProductThreeColumnTable.setBackgroundColor(Color.BLACK,0.7f);
        headerProductThreeColumnTable.addCell(new Cell().add("Description").
                setBold().setFontColor(Color.WHITE).setBorder(Border.NO_BORDER));
        headerProductThreeColumnTable.addCell(new Cell().add("Quantity").
                setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        headerProductThreeColumnTable.addCell(new Cell().add("Price").
                setBold().setFontColor(Color.WHITE).setTextAlignment(TextAlignment.RIGHT)
                .setBorder(Border.NO_BORDER).setMarginRight(15f));
    }

    private void addProductToTable(HashMap<String,Product> hashMap){

        threeCololumnProductTable = new Table(threeColimnWidthProductTable);
        totalSumPrice = 0f;

        for (String i : hashMap.keySet()) {
            double total = hashMap.get(i).getQuantity()*hashMap.get(i).getPrice();
            totalSumPrice+=total;
            threeCololumnProductTable.addCell(new Cell().add(hashMap.get(i).getName()).setBorder(Border.NO_BORDER).setMarginLeft(10f));
            threeCololumnProductTable.addCell(new Cell().add(String.valueOf(hashMap.get(i).getQuantity())).
                    setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
            threeCololumnProductTable.addCell(new Cell().add(String.valueOf(total)).
                    setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
        }
        threeCololumnProductTable.setMarginBottom(20f);
    }
    private void tableDividerDashedBorder(){
        Border dashedBorder = new DashedBorder(Color.GRAY,0.5f);
        tableDividerDashedBorder = new Table(widthTableDivider);
        tableDividerDashedBorder.addCell(new Cell().add("").setBorder(Border.NO_BORDER));
        tableDividerDashedBorder.addCell(new Cell()
                .add(new Table(fullWidthDocument).setBorder(dashedBorder)).setBorder(Border.NO_BORDER));
    }
    private void totalSumProductTable(){
        totalSumProductTable = new Table(threeColimnWidthProductTable);
        totalSumProductTable.addCell(new Cell().add("").setBorder(Border.NO_BORDER).setMarginLeft(10f));
        totalSumProductTable.addCell(new Cell().add("Total").setTextAlignment(TextAlignment.CENTER).setBorder(Border.NO_BORDER));
        totalSumProductTable.addCell(new Cell().add(String.valueOf(totalSumPrice)).
                setTextAlignment(TextAlignment.RIGHT).setBorder(Border.NO_BORDER).setMarginRight(15f));
    }

    private void createPdfDocument(){
        document.add(tableHeader);
        document.add(paragraph);
        document.add(tableDividerSolidBorder);
        document.add(paragraph);
        document.add(tableSellerAndCustomer);
        document.add(paragraph);
        document.add(productParagraph);
        document.add(headerProductThreeColumnTable);
        document.add(threeCololumnProductTable);
        document.add(tableDividerDashedBorder);
        document.add(totalSumProductTable);
        document.close();
    }

    @Override
    public void createDocument(HashMap<String, Product> hashMap){
        createPdf();
        header();
        dividerSolidBorder();
        addDataSellerAndCustomer();
        headerProductTable();
        addProductToTable(hashMap);
        tableDividerDashedBorder();
        totalSumProductTable();
        createPdfDocument();
    }
}
