package view.sorum;

import be.quodlibet.boxable.*;
import be.quodlibet.boxable.line.LineStyle;
import model.Sorum;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

public class SorumPdfExport {
    public void export(List<Sorum> sorumList) throws IOException {
        String outputFileName = System.getProperty("user.dir") +
                File.separator + "pdf" + File.separator + "Nota_Sorum_" + UUID.randomUUID()
                + ".pdf";

        // Buat objek untuk setting font menggunakan helvetica
        PDFont fontPlain = PDType1Font.HELVETICA;
        PDFont fontBold = PDType1Font.HELVETICA_BOLD;

        // Buat dokumen baru lalu buat halaman baru pada dokumen dengan ukuran A4
        PDDocument document = new PDDocument();
        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);

        // Buat object yang dapat menampung aliran byte untuk konten
        PDPageContentStream cos = new PDPageContentStream(document, page);

        // Setting margin
        float margin = 50;

        // menentukan posisi awal y, mengurangi tinggi halaman dengan margin atas dan
        // bawah

        float yStartNewPage = page.getMediaBox().getHeight() - (2 * margin);
        // lebar tabel disetting agar memenuhi lebar halaman
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);

        boolean drawContent = true;
        float bottomMargin = 70;

        // posisi y tabel adalah di atas
        BaseTable table = new BaseTable(yStartNewPage, yStartNewPage, bottomMargin, tableWidth, margin, document, page,
                true, drawContent);

        // buat baris baru dengan tinggi 50
        Row<PDPage> headerRow = table.createRow(50);
        // buat sel untuk judul dengan lebar 100
        Cell<PDPage> cell = headerRow.createCell(100, "<<< Nota Sorum >>>");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFont(fontBold);
        cell.setFontSize(20);
        // vertical alignment diset middle agar posisi teks di tengah
        cell.setValign(VerticalAlignment.MIDDLE);
        // setting gaya border, warna dan lebar
        cell.setTopBorderStyle(new LineStyle(Color.BLACK, 15));
        table.addHeaderRow(headerRow);

        Row<PDPage> row = table.createRow(20);
        cell = row.createCell(8, "No");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFont(fontBold);
        cell.setFontSize(15);
        cell = row.createCell(20, "Nama pembeli");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFont(fontBold);
        cell.setFontSize(15);
        cell = row.createCell(20, "Alamat");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFontSize(15);
        cell = row.createCell(15, "Jenis Motor");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFontSize(15);
        cell = row.createCell(20, "Merek");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFontSize(15);
        cell = row.createCell(17, "Nama motor");
        cell.setAlign(HorizontalAlignment.CENTER);
        cell.setFontSize(15);

        cell.setFont(fontPlain);
        int no = 1;

        for (Sorum sorum : sorumList) {
            row = table.createRow(20);
            cell = row.createCell(8, String.valueOf(no));
            cell.setFontSize(15);
            cell.setAlign(HorizontalAlignment.RIGHT);
            cell = row.createCell(20, sorum.getNamapem());
            cell.setFontSize(15);
            cell = row.createCell(20, sorum.getAlamat());
            cell.setFontSize(15);
            cell = row.createCell(15, sorum.getJenismot());
            cell.setFontSize(15);
            cell = row.createCell(20, sorum.getMermo().getMerekmotor());
            cell.setFontSize(15);
            cell = row.createCell(17, sorum.getNamot().getNamamotor());
            cell.setFontSize(15);
            no++;
        }

        table.draw();
        // tutup aliran konten
        cos.close();
        // Simpan file hasil export
        document.save(outputFileName);
        document.close();
        File file = new File(outputFileName);
        Desktop desktop = Desktop.getDesktop();
        if (file.exists())
            desktop.open(file);
    }
}