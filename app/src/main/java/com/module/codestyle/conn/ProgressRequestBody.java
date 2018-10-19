package com.module.codestyle.conn;


import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.Okio;
import okio.Source;

/**
 * Placet
 * Created by Bachman Sabar MN on 08/02/2017.
 * Email : sabar.cor@gmail.com
 */

public class ProgressRequestBody extends RequestBody {


    private static final int UPLOAD_PROGRESS_BUFFER_SIZE = 8192;
    private MediaType mContentType;

    public interface ProgressCallback {
        public void onProgress(long progress, long total);
    }

    private File mFile;
    private ProgressCallback mListener;

    public ProgressRequestBody(MediaType contentType, File file, ProgressCallback listener) {
        mFile = file;
        mListener = listener;
        mContentType = contentType;
    }

    @Override
    public MediaType contentType() {
        // TODO: 08/02/2017 check again later
//        return MediaType.parse("application/octet-stream");
        return mContentType;
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        Source source = null;
        long fileLength = mFile.length();
        try {
            source = Okio.source(mFile);
            long total = 0;
            long read;

            while ((read = source.read(sink.buffer(), UPLOAD_PROGRESS_BUFFER_SIZE)) != -1) {
                total += read;
                sink.flush();
                mListener.onProgress(total, fileLength);

            }
        } finally {
            Util.closeQuietly(source);
        }

//        long fileLength = mFile.length();
//        byte[] buffer = new byte[UPLOAD_PROGRESS_BUFFER_SIZE];
//        FileInputStream in = new FileInputStream(mFile);
//        long uploaded = 0;
//        try {
//            int read;
//            while ((read = in.read(buffer)) != -1) {
//                mListener.onProgress(uploaded, fileLength);
//                uploaded += read;
//
//                sink.write(buffer, 0, read);
//            }
//        } finally {
//            in.close();
//        }
    }

    @Override
    public long contentLength() throws IOException {
        return mFile.length();
    }
}
