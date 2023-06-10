package sl.com.eightdigitz.authentication.presentation.contact;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\t\u0010\u0013\u001a\u00020\u0003H\u00c2\u0003J\u000e\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017J\u0013\u0010\u0018\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u00c6\u0001J\u0013\u0010\u0019\u001a\u00020\u00152\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u00d6\u0003J\t\u0010\u001c\u001a\u00020\u001dH\u00d6\u0001J\u000e\u0010\u001e\u001a\u00020\u00152\u0006\u0010\u001f\u001a\u00020 J\b\u0010!\u001a\u00020\"H\u0014J\t\u0010#\u001a\u00020\u000eH\u00d6\u0001J\u000e\u0010$\u001a\u00020\"2\u0006\u0010%\u001a\u00020&R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\'"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/contact/JoinContactViewModel;", "Landroidx/lifecycle/ViewModel;", "authUseCase", "Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;", "(Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "liveDataContact", "Landroidx/lifecycle/MutableLiveData;", "Lsl/com/eightdigitz/presentation/Resource;", "Lsl/com/eightdigitz/core/model/domain/DContactUs;", "getLiveDataContact", "()Landroidx/lifecycle/MutableLiveData;", "liveDataContactImage", "", "getLiveDataContactImage", "liveDataJoinUs", "Lsl/com/eightdigitz/core/model/domain/DJoinUs;", "getLiveDataJoinUs", "component1", "contactSupport", "", "contactUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/ContactUsRequest;", "copy", "equals", "other", "", "hashCode", "", "joinUs", "joinUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/JoinUsRequest;", "onCleared", "", "toString", "uploadAvatar", "imageFile", "Ljava/io/File;", "authentication_debug"})
public final class JoinContactViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DJoinUs>> liveDataJoinUs = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DContactUs>> liveDataContact = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<java.lang.String>> liveDataContactImage = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DJoinUs>> getLiveDataJoinUs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DContactUs>> getLiveDataContact() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<java.lang.String>> getLiveDataContactImage() {
        return null;
    }
    
    public final void uploadAvatar(@org.jetbrains.annotations.NotNull()
    java.io.File imageFile) {
    }
    
    public final boolean joinUs(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest joinUsRequest) {
        return false;
    }
    
    public final boolean contactSupport(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest contactUsRequest) {
        return false;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public JoinContactViewModel(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase) {
        super();
    }
    
    private final sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final sl.com.eightdigitz.authentication.presentation.contact.JoinContactViewModel copy(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public java.lang.String toString() {
        return null;
    }
    
    @java.lang.Override()
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override()
    public boolean equals(@org.jetbrains.annotations.Nullable()
    java.lang.Object p0) {
        return false;
    }
}