// Source code is decompiled from a .class file using FernFlower decompiler.
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

class Client implements Runnable {
   private Socket socket;

   Client(Socket var1) {
      this.socket = var1;
   }

   public void run() {
      try {
         DataInputStream var1 = new DataInputStream(this.socket.getInputStream());

         try {
            DataOutputStream var2 = new DataOutputStream(this.socket.getOutputStream());

            try {
               String var3 = var1.readUTF();
               String var4;
               if (!var3.equals("message")) {
                  if (var3.equals("file")) {
                     var4 = var1.readUTF();
                     long var5 = var1.readLong();
                     File var7 = new File("received_" + var4);
                     FileOutputStream var8 = new FileOutputStream(var7);

                     try {
                        byte[] var9 = new byte[4096];
                        long var11 = 0L;

                        while(true) {
                           int var10;
                           if ((var10 = var1.read(var9, 0, Math.min(var9.length, (int)(var5 - var11)))) > 0) {
                              var11 += (long)var10;
                              var8.write(var9, 0, var10);
                              if (var11 < var5) {
                                 continue;
                              }
                           }

                           System.out.println("File received: " + var7.getAbsolutePath());
                           break;
                        }
                     } catch (Throwable var16) {
                        try {
                           var8.close();
                        } catch (Throwable var15) {
                           var16.addSuppressed(var15);
                        }

                        throw var16;
                     }

                     var8.close();
                     var2.writeUTF("File received successfully.");
                  }
               } else {
                  while(true) {
                     var4 = var1.readUTF();
                     System.out.println("Client: " + var4);
                     if (var4.equalsIgnoreCase("bye")) {
                        var2.writeUTF("Goodbye! Connection closed.");
                        System.out.println("Client ended the chat.");
                        break;
                     }

                     var2.writeUTF("Received: " + var4);
                  }
               }

               this.socket.close();
            } catch (Throwable var17) {
               try {
                  var2.close();
               } catch (Throwable var14) {
                  var17.addSuppressed(var14);
               }

               throw var17;
            }

            var2.close();
         } catch (Throwable var18) {
            try {
               var1.close();
            } catch (Throwable var13) {
               var18.addSuppressed(var13);
            }

            throw var18;
         }

         var1.close();
      } catch (IOException var19) {
         System.out.println("Client disconnected.");
      }

   }
}
