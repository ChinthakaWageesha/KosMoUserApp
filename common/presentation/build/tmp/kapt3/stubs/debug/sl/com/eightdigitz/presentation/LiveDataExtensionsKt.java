package sl.com.eightdigitz.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 2, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a-\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007\u001a(\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u001a\u001c\u0010\n\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u0003\u001a5\u0010\u000b\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00040\u00032\u0006\u0010\f\u001a\u0002H\u00022\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t\u00a2\u0006\u0002\u0010\r\u00a8\u0006\u000e"}, d2 = {"setError", "", "T", "Landroidx/lifecycle/MutableLiveData;", "Lsl/com/eightdigitz/presentation/Resource;", "errorCode", "", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Integer;)V", "message", "Lsl/com/eightdigitz/presentation/PMessage;", "setLoading", "setSuccess", "data", "(Landroidx/lifecycle/MutableLiveData;Ljava/lang/Object;Lsl/com/eightdigitz/presentation/PMessage;)V", "presentation_debug"})
public final class LiveDataExtensionsKt {
    
    public static final <T extends java.lang.Object>void setSuccess(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<T>> $this$setSuccess, T data, @org.jetbrains.annotations.Nullable()
    sl.com.eightdigitz.presentation.PMessage message) {
    }
    
    public static final <T extends java.lang.Object>void setLoading(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<T>> $this$setLoading) {
    }
    
    public static final <T extends java.lang.Object>void setError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<T>> $this$setError, @org.jetbrains.annotations.Nullable()
    sl.com.eightdigitz.presentation.PMessage message) {
    }
    
    public static final <T extends java.lang.Object>void setError(@org.jetbrains.annotations.NotNull()
    androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<T>> $this$setError, @org.jetbrains.annotations.Nullable()
    java.lang.Integer errorCode) {
    }
}