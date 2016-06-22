/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import Class.Categoria;
import Class.Compra;
import Class.Cliente;
import Class.Entrega;
import Class.EntregasRealizadas;
import Class.MaisVendidos;
import Class.Produto;
import Negocios.NegocioRelatorio;
import Negocios.NegociosCategoria;
import Negocios.NegociosCliente;
import Negocios.NegociosCompra;
import Negocios.NegociosProduto;
import com.itextpdf.awt.PdfGraphics2D;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Color;
import java.awt.Composite;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.Paint;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphVector;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ImageObserver;
import java.awt.image.RenderedImage;
import java.awt.image.renderable.RenderableImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.AttributedCharacterIterator;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author   Marilia Nayara, Gustavo, Eddi, Patricia

 */
public class TelaRelatorio {
    
    public void GerarPDF(){
        Document documentoPDF = new Document();
        
        try{
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream("C:\\Users\\Marilia Nayara\\Desktop\\PDFRelatorio.pdf"));
            
            documentoPDF.open();
            
            documentoPDF.setPageSize(PageSize.A4);
            
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            
            Paragraph cabecalho = new Paragraph("Teste De SI", fonte);
            
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(cabecalho);
            
            Image imagem = Image.getInstance("C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\src\\View\\imagem2.jpg");
     
            
            imagem.scaleToFit(400, 300);
            
            documentoPDF.add(imagem);
            
        }catch(DocumentException de){
            de.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            documentoPDF.close();
        }
    }
    
    public void GerarEntrega(int codigo) throws IOException{
        NegociosCompra nc = new NegociosCompra();
        Compra compra = nc.ConsultarCompra(codigo);
        
        Document documentoPDF = new Document();
        String output = "C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\Relatorios\\VendasEntrega\\Compra"+codigo+".pdf";
        try{
            
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(output));
            
            documentoPDF.open();
            
            documentoPDF.setPageSize(PageSize.A4);
            
            Image imagem = Image.getInstance("C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\src\\View\\image.png");
            
            imagem.scaleToFit(100, 75);
            
            imagem.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(imagem);
            
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
            
            Paragraph agr = new Paragraph("Aplicação para Gestão de Restaurantes", fonte1);
            
            agr.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(agr);
            
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            
            Paragraph cabecalho = new Paragraph("COMPRA ENTREGA", fonte);
            
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(cabecalho);
            
            documentoPDF.add(new Paragraph("\n"));
            
            Cliente c = new NegociosCliente().ProcurarCliente(compra.getCpf_c());
            
            Timestamp t = compra.getData();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");         
            String a = formato.format(t);
            
            PdfPTable dadosC = new PdfPTable(new float[]{0.10f, 0.70f, 0.20f});
            
            List<String> dados = new ArrayList<String>();
            
            dados.add("CODIGO DA COMPRA");
            dados.add("CLIENTE");
            dados.add("DATA DA COMPRA");
            dados.add(""+compra.getCodigo());
            dados.add(c.getNome());
            dados.add(a);
            
            for (String s : dados) {
                dadosC.addCell(s);
            }
 
            dadosC.setWidthPercentage(110f);
        documentoPDF.add(dadosC);
            
            documentoPDF.add(new Paragraph("\n"));
            /*documentoPDF.add(new Paragraph(" LISTA DE PRODUTOS COMPRADOS"));
            documentoPDF.add(new Paragraph("\n"));
            documentoPDF.add(new Paragraph("Codigo       Produto                                         Valor Unitário          Quantidade             Valor   "));
            String tam = "Codigo       Produto                                         Valor Unitário          Quantidade             Valor   ";
            JOptionPane.showMessageDialog(null, tam.length());
            for(int x=0;x<compra.getLista_prod().size();x++){
                Produto p = new NegociosProduto().ProcurarProduto(compra.getLista_prod().get(x).intValue());
                String cod = ""+p.getCodigo();
                String nome = p.getNome();
                float pv = p.getPvenda();
                String pvs = ""+pv;
                int quant = compra.getQuantidades().get(x).intValue();
                String squant = ""+quant;
                float pf = pv*quant;
                String spf = ""+pf;
                
                int espaco = 13 - cod.length();
                String esp = "";        
                    for(int y = 0;y <espaco;y++){
                           esp=esp+" "; 
                        }
                cod = cod+esp;
                    
                espaco = 49 - nome.length();
                esp = "";
                    for(int y = 0;y <espaco;y++){
                           esp=esp+" "; 
                    }
                nome = nome + esp;
                
                espaco = 24 - pvs.length();
                esp = "";
                    for(int y = 0;y <espaco;y++){
                               esp=esp+" "; 
                    }
                pvs = pvs + esp;
                
                espaco = 23 - squant.length();
                esp = "";
                    for(int y = 0;y <espaco;y++){
                               esp=esp+" "; 
                    }
                squant = squant + esp;
                
                espaco = 8 - spf.length();
                esp = "";
                    for(int y = 0;y <espaco;y++){
                               esp=esp+" "; 
                    }
                spf = spf + esp;
                
                String fina = cod+nome+pvs+squant+spf;
                //JOptionPane.showMessageDialog(null, fina.length());
                documentoPDF.add(new Paragraph(fina));
            }*/
            Font fonte3 = new Font(Font.FontFamily.TIMES_ROMAN, 14, Font.NORMAL);
            
            // Criando uma tabela com 3 colunas
        PdfPTable table = new PdfPTable(new float[]{ 0.1f, 0.45f, 0.15f, 0.15f, 0.15f });
        
        table.setWidthPercentage(110.f);
        // Título para a tabela
        Paragraph tableHeader = new Paragraph("LISTA DE PRODUTOS COMPRADOS", fonte3);
 
        PdfPCell header = new PdfPCell(tableHeader);
        // Definindo que o header vai ocupar as 3 colunas
        header.setColspan(5);
        // Definindo alinhamento do header
        header.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        // Adicionando o header à tabela
        table.addCell(header);
        
        List<String> list = new ArrayList<String>();
 
        list.add("CODIGO");
        list.add("PRODUTO");
        list.add("VALOR UNITÁRIO");
        list.add("QUANTIDADE");
        list.add("VALOR");
        
        for(int x=0;x<compra.getLista_prod().size();x++){
                Produto p = new NegociosProduto().ProcurarProduto(compra.getLista_prod().get(x).intValue());
                String cod = ""+p.getCodigo();
                String nome = p.getNome();
                float pv = p.getPvenda();
                String pvs = ""+pv;
                int quant = compra.getQuantidades().get(x).intValue();
                String squant = ""+quant;
                float pf = pv*quant;
                String spf = ""+pf;
                list.add(cod);
                list.add(nome);
                list.add(pvs);
                list.add(squant);
                list.add(spf);
        }
 
        for (String s : list) {
            table.addCell(s);
        }
 
        documentoPDF.add(table);
            
        documentoPDF.add(new Paragraph("\n"));    
        
            Paragraph total = new Paragraph("Total: " + compra.getPreco_final(), fonte3);
            
            total.setAlignment(Element.ALIGN_RIGHT);
            
            documentoPDF.add(total);    
            
            documentoPDF.add(new Paragraph("\n"));
            
            PdfPTable endereco = new PdfPTable( new float[] {0.30f, 0.80f});
            
            endereco.setWidthPercentage(60f);
            
            endereco.setHorizontalAlignment(Element.ALIGN_CENTER);
            
            Paragraph tableH = new Paragraph("ENDEREÇO DE ENTREGA", fonte3);
 
            PdfPCell hea = new PdfPCell(tableH);
        
            hea.setColspan(2);
        
            hea.setHorizontalAlignment(Paragraph.ALIGN_CENTER);
        
            endereco.addCell(hea);
            
            List<String> dadosE = new ArrayList<String>();
            
            dadosE.add("LOGRADOURO");
            dadosE.add(c.getEndereco().getRua());
            dadosE.add("NUMERO");
            dadosE.add(""+c.getEndereco().getNumero());
            dadosE.add("BAIRRO");
            dadosE.add(c.getEndereco().getBairro());
            dadosE.add("CIDADE");
            dadosE.add(c.getEndereco().getCidade());
            dadosE.add("ESTADO");
            dadosE.add(c.getEndereco().getEstado());
            
            for(String s : dadosE){
                endereco.addCell(s);
            }
            
            documentoPDF.add(endereco);
            
            documentoPDF.add(new Paragraph("\n"));
            
            documentoPDF.add(new Paragraph("Assinatura:  __________________________________________________________"));
            
            Paragraph ass = new Paragraph("("+c.getNome()+")",fonte1);
            
            ass.setAlignment(Element.ALIGN_CENTER);
            documentoPDF.add(ass);

            
            documentoPDF.add(new Paragraph("----------------------------------------------------------------------------------------------------------------------------------"));
            
            Paragraph comp = new Paragraph("COMPROVANTE DE ENTREGA");
            
            
            comp.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(comp);
            
            documentoPDF.add(new Paragraph("\n"));
            
            documentoPDF.add(dadosC);
            
            documentoPDF.add(total);
            
            documentoPDF.add(new Paragraph("Assinatura:  __________________________________________________________"));
            
            documentoPDF.add(ass);
            
        }catch(DocumentException de){
            de.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            documentoPDF.close();
            java.awt.Desktop.getDesktop().open( new File(output));
        }
    }
    
    public void GerarVendidosSemana(String inicio, String Final) throws FileNotFoundException, BadElementException, IOException{
        NegocioRelatorio nr = new NegocioRelatorio();
        
        Document documentoPDF = new Document();
        String nome = inicio.toString();
        String comp = Final.toString();
        String output = "C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\Relatorios\\MaisVendidos\\MVdia.pdf";
        try{
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(output));
            
            documentoPDF.open();
            
            documentoPDF.setPageSize(PageSize.A4);
            
            Image imagem = Image.getInstance("C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\src\\View\\image.png");
            
            imagem.scaleToFit(100, 75);
            
            imagem.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(imagem);
            
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
            
            Paragraph agr = new Paragraph("Aplicação para Gestão de Restaurantes", fonte1);
            
            agr.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(agr);
            
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            
            Paragraph cabecalho = new Paragraph("MAIS VENDIDAS DA SEMANA", fonte);
            
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(cabecalho);
            
            documentoPDF.add(new Paragraph("\n"));
            
            
            
                List<MaisVendidos> mv = nr.MaisVendidos(inicio, Final);
            
            
            System.out.println("voltou");
             SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            List<Timestamp> gamb = new ArrayList<Timestamp>();
            String aux;
            String aux1;
            for(int j=0;j<mv.size();j++){
            int gam = 0;
            Date d = new Date(mv.get(j).getData().getTime());
            aux = formato.format(d);
            
            for(int i=0; i<gamb.size();i++){
                
                aux1 = formato.format(gamb.get(i).getTime());

                if(aux.equals(aux1)){
                    gam=1;
                    System.out.println("entroy aki");
                }
            }
            
            if(gam==0){
            gamb.add(mv.get(j).getData());
                System.out.println(j);       
            String a = formato.format(mv.get(j).getData());
            Calendar cal = Calendar.getInstance();  
            cal.setTime(mv.get(j).getData());//  ========> Objeto Date  
            int day = cal.get(Calendar.DAY_OF_WEEK); 
            
            Paragraph dia = new Paragraph();
            if(day==1){
                dia = new Paragraph("DOMINGO                      "+ a);
            }else if(day==2){
                dia = new Paragraph("SEGUNDA-FEIRA                     "+ a);
            }else if(day==3){
                dia = new Paragraph("TERÇA-FEIRA                     "+ a);
            }else if(day==4){
                dia = new Paragraph("QUARTA-FEIRA                     "+ a);
            }else if(day==5){
                dia = new Paragraph("QUINTA-FEIRA                     "+ a);
            }else if(day==6){
                dia = new Paragraph("SEXTA-FEIRA                     "+ a);
            }else if(day==7){
                dia = new Paragraph("SABADO                     "+ a);
            }
            documentoPDF.add(dia);
            documentoPDF.add(new Paragraph("\n"));
            PdfPTable dadosC = new PdfPTable(new float[]{0.30f, 0.50f, 0.15f, 0.15f});
            
            List<String> dados = new ArrayList<String>();
            
            dados.add("CATEGORIA");
            dados.add("PRODUTO");
            dados.add("QUANTIDADE");
            dados.add("VALOR");
            
            List<String> categorias = new ArrayList<String>();
            for(int x = 0; x < mv.size();x++){
                String aux2 = formato.format(mv.get(x).getData().getTime());
                int contc=0;        
                System.out.println(aux);
                System.out.println(aux2);
                if(aux.equals(aux2)){
                    Categoria c = new NegociosCategoria().ProcurarCategoria(mv.get(x).getIdent());
                    for(int k=0;k<categorias.size();k++){
                        if(c.getNome().equals(categorias.get(k).toString())){
                            contc=1;
                        }
                    }
                    if(contc==0){
                        categorias.add(c.getNome());
                        dados.add(c.getNome());
                        Produto p = new NegociosProduto().ProcurarProduto(mv.get(x).getCodigo());
                        dados.add(p.getNome());
                        dados.add(""+mv.get(x).getQuantidade());
                        double valor = mv.get(x).getQuantidade()*mv.get(x).getPtotal();
                        dados.add(""+valor);
                    }
                }
            }
            
            for (String s : dados) {
                dadosC.addCell(s);
            }
 
            dadosC.setWidthPercentage(110f);
        documentoPDF.add(dadosC);
            }
            
            }
        }catch(DocumentException de){
            de.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            documentoPDF.close();
            java.awt.Desktop.getDesktop().open( new File(output));
        }
    }
    
    public void GerarEntregas() throws DocumentException, FileNotFoundException, BadElementException, IOException{
       NegocioRelatorio nr = new NegocioRelatorio();
        
        Document documentoPDF = new Document();
        String output = "C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\Relatorios\\MaisVendidos\\EntregasRealizadas.pdf";
        try{
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(output));
            
            documentoPDF.open();
            
            documentoPDF.setPageSize(PageSize.A4);
            
            Image imagem = Image.getInstance("C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\src\\View\\image.png");
            
            imagem.scaleToFit(100, 75);
            
            imagem.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(imagem);
            
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
            
            Paragraph agr = new Paragraph("Aplicação para Gestão de Restaurantes", fonte1);
            
            agr.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(agr);
            
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            
            Paragraph cabecalho = new Paragraph("ENTREGAS REALIZADAS", fonte);
            
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(cabecalho);
            
            documentoPDF.add(new Paragraph("\n\n"));
            
            List<EntregasRealizadas> ER = nr.EntregasRealizadas();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            int d=0, se=0, te=0, qa=0, qi=0, sex=0, sa=0;
            
            for(int x=0;x<ER.size();x++){
                String a = formato.format(ER.get(x).getDatas());
                Calendar cal = Calendar.getInstance();  
                cal.setTime(ER.get(x).getDatas());//  ========> Objeto Date  
                int day = cal.get(Calendar.DAY_OF_WEEK); 

                if(day==1){
                    d = d + ER.get(x).getQuant();
                }else if(day==2){
                    se = se + ER.get(x).getQuant();
                }else if(day==3){
                    te = te + ER.get(x).getQuant();
                }else if(day==4){
                    qa = qa + ER.get(x).getQuant();
                }else if(day==5){
                    qi = qi + ER.get(x).getQuant();
                }else if(day==6){
                    sex = sex + ER.get(x).getQuant();
                }else if(day==7){
                    sa = sa + ER.get(x).getQuant();
                }
            }
            
            PdfPTable dadosC = new PdfPTable(new float[]{0.50f, 0.50f});
            
            List<String> dados = new ArrayList<String>();
            
            dados.add("DIA");
            dados.add("QUANTIDADE");
            dados.add("DOMINGO");
            dados.add(""+d);
            dados.add("SEGUNDA-FEIRA");
            dados.add(""+se);
            dados.add("TERÇA-FEIRA");
            dados.add(""+te);
            dados.add("QUARTA-FEIRA");
            dados.add(""+qa);
            dados.add("QUINTA-FEIRA");
            dados.add(""+qi);
            dados.add("SEXTA-FEIRA");
            dados.add(""+sex);
            dados.add("SABADO");
            dados.add(""+sa);
            
            for (String s : dados) {
                dadosC.addCell(s);
            }
 
            dadosC.setWidthPercentage(60f);
            dadosC.setHorizontalAlignment(Element.ALIGN_CENTER);
            documentoPDF.add(dadosC);
            
        }catch(DocumentException de){
            de.printStackTrace();
        }finally{
            documentoPDF.close();
            java.awt.Desktop.getDesktop().open( new File(output));
        }
    }
    
    public void EntregasvsMesa(){
        NegocioRelatorio nr = new NegocioRelatorio();
        
        Document documentoPDF = new Document();
        String output = "C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\Relatorios\\MaisVendidos\\EntregasVSMesa.pdf";
        try{
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(output));
            
            documentoPDF.open();
            
            documentoPDF.setPageSize(PageSize.A4);
            
            Image imagem = Image.getInstance("C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\src\\View\\image.png");
            
            imagem.scaleToFit(100, 75);
            
            imagem.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(imagem);
            
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
            
            Paragraph agr = new Paragraph("Aplicação para Gestão de Restaurantes", fonte1);
            
            agr.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(agr);
            
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            
            Paragraph cabecalho = new Paragraph("VENDAS", fonte);
            
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(cabecalho);
            
            Paragraph paragrafo = new Paragraph("Entre Mesas e Entregas");
            
            paragrafo.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(paragrafo);
            documentoPDF.add(new Paragraph("\n\n"));
            
            List<Entrega> Entrega = nr.Entregas();
            List<Entrega> Mesa = nr.Mesa();
            
            for(int x=0; x<7;x++){
            
            String domingo = "DOMINGO";
            String segunda = "SEGUNDA-FEIRA";
            String terça = "TERÇA-FEIRA";
            String quarta = "QUARTA-FEIRA"; 
            String quinta = "QUINTA-FEIRA";
            String sexta = "SEXTA-FEIRA";
            String sabado = "SÁBADO";
            String Comp = new String();    
            if(x==0){
                documentoPDF.add(new Paragraph(domingo));
                Comp = domingo;
            }else if(x==1){
                documentoPDF.add(new Paragraph(segunda));
                Comp = segunda;
            }else if(x==2){
                documentoPDF.add(new Paragraph(terça));
                Comp = terça;
            }else if(x==3){
                Comp = quarta;
                documentoPDF.add(new Paragraph(quarta));
            }else if(x==4){
                Comp = quinta;
                documentoPDF.add(new Paragraph(quinta));
            }else if(x==5){
                Comp = sexta;
                documentoPDF.add(new Paragraph(sexta));
            }else if(x==6){
                Comp = sabado;
                documentoPDF.add(new Paragraph(sabado));
            }
            
            documentoPDF.add(new Paragraph("\n"));
            
            PdfPTable dadosC = new PdfPTable(new float[]{0.30f, 0.30f, 0.40f});
            
            List<String> dados = new ArrayList<String>();
            
            dados.add("MESA");
            dados.add("ENTREGA");
            dados.add("PORCENTAGEM %");
            int temmesa=0, tementrega=0;
            int tam = Mesa.size();
            int tam1 = Entrega.size();
            int aux;
            if(tam<tam1) aux = tam1;
            else aux = tam;
            double mesa=0, entrega=0;    
            for(int y = 0; y<aux; y++){
               
                if(y<Mesa.size()){
                    if(Comp.equals(Mesa.get(y).getDia().trim())){
                        mesa = Mesa.get(y).getQuant();
                    }
                }
                if(y<Entrega.size()){
                    if(Entrega.get(y).getDia().trim().equals(Comp)){
                        entrega = Entrega.get(y).getQuant();
                    }
                }
            }
            dados.add(""+mesa);
            dados.add(""+entrega);
            double total = mesa+entrega;
            if(total==0){ 
                dados.add("0% Mesa - 0% Entrega");
            }else{
            mesa = (mesa/total) *100;
            entrega = (entrega/total) *100;
            String M = String.format("%.2f", mesa);
            String E = String.format("%.2f", entrega);
            dados.add(M+"% Mesa - "+E+"% Entrega");
            }
            for (String s : dados) {
                dadosC.addCell(s);
            }
 
            dadosC.setWidthPercentage(100f);
            dadosC.setHorizontalAlignment(Element.ALIGN_CENTER);
            documentoPDF.add(dadosC);
            }
            
            
        } catch (DocumentException ex) {
            Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            documentoPDF.close();
            try {
                java.awt.Desktop.getDesktop().open( new File(output));
            } catch (IOException ex) {
                Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void EntregasPorLocalidade(){
        NegocioRelatorio nr = new NegocioRelatorio();
        
        Document documentoPDF = new Document();
        String output = "C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\Relatorios\\MaisVendidos\\EntregasPorLocalidades.pdf";
        try{
            
            PdfWriter.getInstance(documentoPDF, new FileOutputStream(output));
            
            documentoPDF.open();
            
            documentoPDF.setPageSize(PageSize.A4);
            
            Image imagem = Image.getInstance("C:\\Users\\Marilia Nayara\\Documents\\NetBeansProjects\\SistemaInformação\\src\\View\\image.png");
            
            imagem.scaleToFit(100, 75);
            
            imagem.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(imagem);
            
            Font fonte1 = new Font(Font.FontFamily.TIMES_ROMAN, 8, Font.BOLD);
            
            Paragraph agr = new Paragraph("Aplicação para Gestão de Restaurantes", fonte1);
            
            agr.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(agr);
            
            Font fonte = new Font(Font.FontFamily.TIMES_ROMAN, 24, Font.BOLD);
            
            Paragraph cabecalho = new Paragraph("ENTREGAS POR LOCALIDADE", fonte);
            
            cabecalho.setAlignment(Element.ALIGN_CENTER);
            
            documentoPDF.add(cabecalho);
            
            documentoPDF.add(new Paragraph("\n\n"));
            
            List<Entrega> Entrega = nr.Localidades();
            
            PdfPTable dadosC = new PdfPTable(new float[]{0.50f, 0.50f});
            
            List<String> dados = new ArrayList<String>();
            
            dados.add("BAIRRO");
            dados.add("TOTAL DE ENTREGAS");
            
            for(int x=0;x<Entrega.size();x++){
                dados.add(Entrega.get(x).getDia());
                dados.add(""+Entrega.get(x).getQuant());
            }
            
            for (String s : dados) {
                dadosC.addCell(s);
            }
 
            dadosC.setWidthPercentage(100f);
            dadosC.setHorizontalAlignment(Element.ALIGN_CENTER);
            documentoPDF.add(dadosC);

        } catch (DocumentException ex) {
            Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            documentoPDF.close();
            try {
                java.awt.Desktop.getDesktop().open( new File(output));
            } catch (IOException ex) {
                Logger.getLogger(TelaRelatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
}
