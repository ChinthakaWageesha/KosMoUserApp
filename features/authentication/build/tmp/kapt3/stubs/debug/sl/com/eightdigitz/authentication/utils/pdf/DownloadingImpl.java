package sl.com.eightdigitz.authentication.utils.pdf;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0002\u001b\u001cB\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u0003H\u0002J\u0014\u0010\u0010\u001a\u00020\n2\n\u0010\u0011\u001a\u00060\u0012j\u0002`\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\n2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0016H\u0002J\u0018\u0010\u0018\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0019\u001a\u00020\u001aH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lsl/com/eightdigitz/authentication/utils/pdf/DownloadingImpl;", "Lsl/com/eightdigitz/authentication/utils/pdf/Download;", "context", "Landroid/content/Context;", "uiThread", "Landroid/os/Handler;", "listener", "Lsl/com/eightdigitz/authentication/utils/pdf/Download$Listener;", "(Landroid/content/Context;Landroid/os/Handler;Lsl/com/eightdigitz/authentication/utils/pdf/Download$Listener;)V", "download", "", "url", "", "fileName", "isOnline", "", "notifyFailure", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "notifyProgress", "downloadedSize", "", "totalSize", "notifySuccess", "destinationPath", "Ljava/io/File;", "Companion", "OnDownloadingListener", "authentication_debug"})
public final class DownloadingImpl implements sl.com.eightdigitz.authentication.utils.pdf.Download {
    private final sl.com.eightdigitz.authentication.utils.pdf.Download.Listener listener = null;
    private final android.content.Context context = null;
    private final android.os.Handler uiThread = null;
    private static final int BUFFER_LENGTH = 1024;
    private static final int NOTIFY_PERIOD = 153600;
    public static final sl.com.eightdigitz.authentication.utils.pdf.DownloadingImpl.Companion Companion = null;
    
    @java.lang.Override()
    public void download(@org.jetbrains.annotations.NotNull()
    java.lang.String url, @org.jetbrains.annotations.NotNull()
    java.lang.String fileName) {
    }
    
    /**
     * Will get a file name from url
     * @param url
     */
    @java.lang.Override()
    public void download(@org.jetbrains.annotations.NotNull()
    java.lang.String url) {
    }
    
    private final void notifySuccess(java.lang.String url, java.io.File destinationPath) {
    }
    
    private final void notifyFailure(java.lang.Exception e) {
    }
    
    private final void notifyProgress(int downloadedSize, int totalSize) {
    }
    
    /**
     * @param context
     * @return true if device online and false if offline
     */
    private final boolean isOnline(android.content.Context context) {
        return false;
    }
    
    public DownloadingImpl(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    android.os.Handler uiThread, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.utils.pdf.Download.Listener listener) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0084\u0004\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0014\u0010\u0003\u001a\u00020\u00042\n\u0010\u0005\u001a\u00060\u0006j\u0002`\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nH\u0016J\u0018\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0016\u00a8\u0006\u0011"}, d2 = {"Lsl/com/eightdigitz/authentication/utils/pdf/DownloadingImpl$OnDownloadingListener;", "Lsl/com/eightdigitz/authentication/utils/pdf/Download$Listener;", "(Lsl/com/eightdigitz/authentication/utils/pdf/DownloadingImpl;)V", "onFailure", "", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "onProgressUpdate", "progress", "", "total", "onSuccess", "url", "", "file", "Ljava/io/File;", "authentication_debug"})
    public final class OnDownloadingListener implements sl.com.eightdigitz.authentication.utils.pdf.Download.Listener {
        
        /**
         * This method will notify main thread that downloading is successful ended.
         * return
         * @param url (for example if person want to save it in a hashMap as a key)
         * and
         * @param file
         */
        @java.lang.Override()
        public void onSuccess(@org.jetbrains.annotations.NotNull()
        java.lang.String url, @org.jetbrains.annotations.NotNull()
        java.io.File file) {
        }
        
        /**
         * this will return any error like no network or can't create new file and so on
         * @param e
         */
        @java.lang.Override()
        public void onFailure(@org.jetbrains.annotations.NotNull()
        java.lang.Exception e) {
        }
        
        /**
         * Wil return current
         * @param progress (current size)
         * and
         * @param total size left
         */
        @java.lang.Override()
        public void onProgressUpdate(int progress, int total) {
        }
        
        public OnDownloadingListener() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006\t"}, d2 = {"Lsl/com/eightdigitz/authentication/utils/pdf/DownloadingImpl$Companion;", "", "()V", "BUFFER_LENGTH", "", "NOTIFY_PERIOD", "extractFileNameFromURL", "", "url", "authentication_debug"})
    public static final class Companion {
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String extractFileNameFromURL(@org.jetbrains.annotations.NotNull()
        java.lang.String url) {
            return null;
        }
        
        private Companion() {
            super();
        }
    }
}