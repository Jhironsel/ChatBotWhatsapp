package bootCore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SistemaBook extends javax.swing.JFrame implements Runnable {

    WebDriver driver;
    String[] contactos = new String[1000];
    int cantTabs = 0;

    static Thread thread;

    public SistemaBook() {
        initComponents();
        thread = new Thread(this);
    }

    public void run() {
        try {
            while (chRevisar.isSelected() == true) {
                //Revisamos que existan nuevo mensajes en el chat de WA
                revisaWA();
                pausa(3000);
            }
        } catch (java.lang.IndexOutOfBoundsException e) {
            thread = new Thread(this);
            chRevisarActionPerformed(null);
        }

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        uriDialogFlow = new rojerusan.RSMetroTextPlaceHolder();
        btnAbrirWhatsAppWeb = new rojeru_san.complementos.RSButtonHover();
        chRevisar = new org.jdesktop.swingx.renderer.JRendererCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sistema de chatBot SoftSureña.");

        uriDialogFlow.setPlaceholder("Ingresa la URI del  DialogFlow");

        btnAbrirWhatsAppWeb.setText("Abrir WhatsApp Web");
        btnAbrirWhatsAppWeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirWhatsAppWebActionPerformed(evt);
            }
        });

        chRevisar.setText("Activar Bot");
        chRevisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chRevisarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(chRevisar, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(uriDialogFlow, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                        .addComponent(btnAbrirWhatsAppWeb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(uriDialogFlow, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrirWhatsAppWeb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(chRevisar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(70, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAbrirWhatsAppWebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirWhatsAppWebActionPerformed
        try {
            //Obtenemos la ubicacion de nuestro programa
            String localPath = new File(".").getCanonicalPath();

            String SO = System.getProperty("os.name");

            //Inicializamos ls opciones de chrome
            ChromeOptions optionsGoo = new ChromeOptions();

            switch (SO) {
                case "Linux":
                    //Leemos el chromedriver que esta en la misma direccion del programa
                    System.setProperty("webdriver.chrome.driver", localPath + "/chromedriver_linux64/chromedriver");

                    //Guardamos la sesion en la carpeta chromeWA
                    optionsGoo.addArguments("--user-data-dir=" + localPath + "/chromeWA");

                    //Permitimos la propiedad no-sandbox para evitar problemas en linux
                    optionsGoo.addArguments("--no-sandbox");
                    break;
                case "Windows 10":
                    System.setProperty("webdriver.chrome.driver", localPath + "\\chromedriver_win32\\chromedriver.exe");
                    //Guardamos la sesion en la carpeta chromeWA
                    optionsGoo.addArguments("--user-data-dir=" + localPath + "\\chromeWA");
                    break;
                default:
                    JOptionPane.showMessageDialog(
                            null,
                            "Error Sistema Operactivo no soportado ",
                            "Error al abrir navegador",
                            JOptionPane.ERROR_MESSAGE);
                    return;
            }

            //Deshabilitamos las notificaciones
            optionsGoo.addArguments("--disable-notifications");

            //Instanciamos un nuevo chromedriver
            driver = new ChromeDriver(optionsGoo);

            //Abrimos whatsapp
            driver.get("https://web.whatsapp.com");

        } catch (IOException ex) {
            Logger.getLogger(SistemaBook.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAbrirWhatsAppWebActionPerformed

    private void chRevisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chRevisarActionPerformed
        try {
            if (chRevisar.isSelected()) {
                thread.start();
            } else {
                thread.interrupt();
            }
        } catch (java.lang.IllegalThreadStateException e) {
            thread = new Thread(this);
            chRevisarActionPerformed(null);
        }

    }//GEN-LAST:event_chRevisarActionPerformed

    public void revisaWA() {
        //Si hay un mensaje
        if (driver.findElements(By.className("aumms1qt")).size() > 0) {
            //Buscamos el nombre del contacto
            String nombreContacto = driver.findElement(
                    By.className("aumms1qt")).findElement(
                    By.xpath("./../../../../..")).findElement(
                    By.className("zoWT4")).getText();

            //Obtenemos la cantidad mensajes enviados
            int cantNuevosMensajes = Integer.parseInt(driver.findElement(By.className("aumms1qt")).getText());

            //Damos click en el contacto para abrir el chat
            driver.findElement(By.className("aumms1qt")).click();

            //Obtenemos todos los mensajes
            List<WebElement> webElementsMsgWA = driver.findElements(By.className("_22Msk"));

            //Variable que almacenara los ultimos mensaje enviados
            String mensajeEnviar = "";
            
            //Ciclo que unira todos los ultimos mensajes
            for (int i = cantNuevosMensajes; i >= 1; i--) {
                //Obtenemos cada mensaje
                String ultimoMensaje = webElementsMsgWA.get(webElementsMsgWA.size() - i).findElement(By.className("i0jNr")).getText();
                //Unimos los mensajes
                mensajeEnviar = mensajeEnviar + " " + ultimoMensaje;
            }

            //Obtenemos todas las TABS
            ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());

            boolean contactoEncontrado = false;
            //Validamos que el nombre de contacto no este en la lista de contactos de lo contrario nos posicionamos en el id del contacto
            for (int tabSel = 1; tabSel <= cantTabs; tabSel++) {
                
                //Si el contacto ya existe en la lista
                if (nombreContacto.equals(contactos[tabSel])) {
                    //Activamos la bandera y decimos que si encontramos el contacto
                    contactoEncontrado = true;
                    //Abrimos el tab del contacto
                    driver.switchTo().window(tabs.get(tabSel));
                    //Rompemos el ciclo
                    break;
                }
            }
            
            //Si no se encontro el contacto lo creamos
            if (contactoEncontrado == false) {
                //aumentamos el numero de contactos
                cantTabs++;
                //Asignamos el nombre del contacto a la lista de contactos
                contactos[cantTabs] = nombreContacto;
                //Abrimos una nueva pestaña
                driver.switchTo().newWindow(WindowType.TAB);
                //Vamos a la url de nuestro dialogFlow
                driver.get(uriDialogFlow.getText());
            }

            pausa(3000);
            //escribimos el mensaje
            driver.findElement(By.id("query")).sendKeys(mensajeEnviar);
            
            
            
            
            revisarMensaje(mensajeEnviar);
            
            
            

            //Enviamos el mensaje haciendo enter
            driver.findElement(By.id("query")).sendKeys(Keys.ENTER);

            //Esperamos 1 segundo en lo que responde
            pausa(3000);

            //Obtenemos todas las respuestas
            List<WebElement> webElementsMsgDF = driver.findElements(By.className("server-response"));

            //Sacamos la ultima respuesta
            String RespuestaDF = webElementsMsgDF.get(webElementsMsgDF.size() - 1).getText();

            //Regresamos a la pestaña de WA
            driver.switchTo().window(tabs.get(0));

            //Escribimos el mensaje
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys(RespuestaDF);

            //Esperamos 1 segundo
            pausa(1500);

            //Precionamos enter
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys(Keys.ENTER);
            
            driver.findElement(By.xpath("//*[@id=\"main\"]/footer/div[1]/div/span[2]/div/div[2]/div[1]/div/div[1]/p")).sendKeys(Keys.ESCAPE);
            
            //Refrescamos el WA web para esperar nuevas preguntas
            //driver.get("https://web.whatsapp.com");
            //pausa(5000);
        }
    }

    //Funcion que espera un tiempo https://www.w3schools.com/java/java_threads.asp
    public void pausa(long sleeptime) {
        try {
            Thread.sleep(sleeptime);
        } catch (InterruptedException ex) {
        }
    }

    public static void main(String args[]) {

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new SistemaBook().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private rojeru_san.complementos.RSButtonHover btnAbrirWhatsAppWeb;
    private org.jdesktop.swingx.renderer.JRendererCheckBox chRevisar;
    private rojerusan.RSMetroTextPlaceHolder uriDialogFlow;
    // End of variables declaration//GEN-END:variables

    private void revisarMensaje(String mensajeEnviar) {
        System.out.println("Mensaje: "+mensajeEnviar);
    }
}