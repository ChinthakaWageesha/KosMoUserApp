package sl.com.eightdigitz.authentication.presentation.verifyOTP;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\u0016J\b\u0010\u001b\u001a\u00020\u0014H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001d\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\f\u00a8\u0006\u001c"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/verifyOTP/OTPViewModel;", "Landroidx/lifecycle/ViewModel;", "authUseCase", "Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;", "(Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "liveDataOTP", "Landroidx/lifecycle/MutableLiveData;", "Lsl/com/eightdigitz/presentation/Resource;", "Lsl/com/eightdigitz/core/model/domain/DOTP;", "getLiveDataOTP", "()Landroidx/lifecycle/MutableLiveData;", "liveDataOTPToken", "Lsl/com/eightdigitz/core/model/domain/DOTPToken;", "getLiveDataOTPToken", "liveDataUser", "Lsl/com/eightdigitz/core/model/domain/DUser;", "getLiveDataUser", "getOTP", "", "phoneNumber", "", "getOTPToken", "otp", "getUserByIDToken", "idToken", "onCleared", "authentication_debug"})
public final class OTPViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTP>> liveDataOTP = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTPToken>> liveDataOTPToken = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DUser>> liveDataUser = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTP>> getLiveDataOTP() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DOTPToken>> getLiveDataOTPToken() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DUser>> getLiveDataUser() {
        return null;
    }
    
    public final void getOTP(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
    }
    
    public final void getOTPToken(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String otp) {
    }
    
    public final void getUserByIDToken(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public OTPViewModel(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase) {
        super();
    }
}