package fr.ryfax.rge.engine.utils.path;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class Resource {

    private static final ClassLoader cl = Thread.currentThread().getContextClassLoader();

    private URL resource;

    public Resource(String path, PathType type) {
        switch (type) {
            case INSIDE:
                this.resource = cl.getResource(path);
                break;
            case OUTSIDE:
                File file = new File(path);
                try {
                    this.resource = file.toURI().toURL();
                } catch (Exception e) { e.printStackTrace(); }
                break;
        }
    }

    /*
     * Getters
     */
    public InputStream getStream() {
        try { return resource.openStream(); }
        catch (IOException e) { e.printStackTrace(); }
        return null;
    }
    public URL getResource() { return resource; }
}
