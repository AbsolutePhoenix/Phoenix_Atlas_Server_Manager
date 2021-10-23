package games.absolutephoenix.phoenixatlasservermanager.rcon;

/*
The MIT License (MIT)

Copyright (c) 2021 Absolute_Phoenix

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit
persons to whom the Software is furnished to do so, subject to the following conditions:
1: All products using this code may not make revenue.
2: Any project using this project or significant portions of this project must give credit to the original copyright holder.
*/

import games.absolutephoenix.phoenixatlasservermanager.rcon.ex.AuthenticationException;

import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Random;

public class Rcon {

    private final Object sync = new Object();
    private final Random rand = new Random();

    private int requestId;
    private Socket socket;

    private Charset charset;

    public Rcon(String host, int port, byte[] password) throws IOException, AuthenticationException {
        this.charset = StandardCharsets.UTF_8;

        this.connect(host, port, password);
    }

    public void connect(String host, int port, byte[] password) throws IOException, AuthenticationException {
        if(host == null || host.trim().isEmpty()) {
            throw new IllegalArgumentException("Host can't be null or empty");
        }

        if(port < 1 || port > 65535) {
            throw new IllegalArgumentException("Port is out of range");
        }
        synchronized(sync) {
            this.requestId = rand.nextInt();
            this.socket = new Socket(host, port);
        }

        RconPacket res = this.send(RconPacket.SERVERDATA_AUTH, password);
        if(res.getRequestId() == -1) {
            throw new AuthenticationException("Password rejected by server");
        }
    }

    public void disconnect() throws IOException {
        synchronized(sync) {
            this.socket.close();
        }
    }

    public String command(String payload) throws IOException {
        if(payload == null || payload.trim().isEmpty()) {
            throw new IllegalArgumentException("Payload can't be null or empty");
        }

        RconPacket response = this.send(RconPacket.SERVERDATA_EXECCOMMAND, payload.getBytes());

        return new String(response.getPayload(), this.getCharset());
    }

    private RconPacket send(int type, byte[] payload) throws IOException {
        synchronized(sync) {
            return RconPacket.send(this, type, payload);
        }
    }

    public int getRequestId() {
        return requestId;
    }

    public Socket getSocket() {
        return socket;
    }

    public Charset getCharset() {
        return charset;
    }

    public void setCharset(Charset charset) {
        this.charset = charset;
    }

}
