package Client;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Network {
    private static Socket socket;
    private static ObjectEncoderOutputStream out;
    private static ObjectDecoderInputStream in;

    public ObjectEncoderOutputStream getOut(){
        return out;
    }
    public ObjectDecoderInputStream getIn(){
        return in;
    }

    public static void start(){
        try{
            socket = new Socket("localhost",8189);
            out = new ObjectEncoderOutputStream(socket.getOutputStream());
            in = new ObjectDecoderInputStream(socket.getInputStream());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void stop(){
        try {
            out.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        try {
            in.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try {
            socket.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void sendMsg(String s){
        try {
            out.writeBytes(String.valueOf(s));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static String readMSg() throws IOException{
        byte s = in.readByte();
        return String.valueOf(s);
    }

}