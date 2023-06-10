package sl.com.eightdigitz.authentication.presentation.registration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001dJ\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010\u001c\u001a\u00020 J\u0006\u0010!\u001a\u00020\u001bJ\b\u0010\"\u001a\u00020\u001bH\u0014J\u000e\u0010#\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020$J\u000e\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020\'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R#\u0010\u0007\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001d\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\rR\u001d\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\rR\u001d\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00180\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\r\u00a8\u0006("}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/registration/RegistrationViewModel;", "Landroidx/lifecycle/ViewModel;", "authUseCase", "Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;", "(Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;)V", "compositeDisposable", "Lio/reactivex/disposables/CompositeDisposable;", "liveDataCategories", "Landroidx/lifecycle/MutableLiveData;", "Lsl/com/eightdigitz/presentation/Resource;", "", "Lsl/com/eightdigitz/core/model/domain/DPreference;", "getLiveDataCategories", "()Landroidx/lifecycle/MutableLiveData;", "liveDataFirebaseToken", "Lsl/com/eightdigitz/core/model/domain/DFirebaseToken;", "getLiveDataFirebaseToken", "liveDataNotification", "Lsl/com/eightdigitz/models/Success;", "getLiveDataNotification", "liveDataPreference", "Lsl/com/eightdigitz/core/model/domain/DUser;", "getLiveDataPreference", "liveDataSaveUser", "Lsl/com/eightdigitz/core/model/presentation/PUser;", "getLiveDataSaveUser", "addUserPreferences", "", "request", "Lsl/com/eightdigitz/client/apiSupports/requests/AddUserPreferenceRequest;", "createAccount", "", "Lsl/com/eightdigitz/client/apiSupports/requests/RegisterRequest;", "getPreferences", "onCleared", "registerFirebaseToken", "Lsl/com/eightdigitz/client/apiSupports/models/FirebaseToken;", "requestWelcomeNotification", "userId", "", "authentication_debug"})
public final class RegistrationViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.presentation.PUser>> liveDataSaveUser = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<java.util.List<sl.com.eightdigitz.core.model.domain.DPreference>>> liveDataCategories = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DUser>> liveDataPreference = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.models.Success>> liveDataNotification = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DFirebaseToken>> liveDataFirebaseToken = null;
    private final io.reactivex.disposables.CompositeDisposable compositeDisposable = null;
    private final sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase = null;
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.presentation.PUser>> getLiveDataSaveUser() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<java.util.List<sl.com.eightdigitz.core.model.domain.DPreference>>> getLiveDataCategories() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DUser>> getLiveDataPreference() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.models.Success>> getLiveDataNotification() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.MutableLiveData<sl.com.eightdigitz.presentation.Resource<sl.com.eightdigitz.core.model.domain.DFirebaseToken>> getLiveDataFirebaseToken() {
        return null;
    }
    
    public final void getPreferences() {
    }
    
    public final void registerFirebaseToken(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.models.FirebaseToken request) {
    }
    
    public final boolean createAccount(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest request) {
        return false;
    }
    
    public final void addUserPreferences(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest request) {
    }
    
    public final void requestWelcomeNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
    
    public RegistrationViewModel(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.domain.usecase.AuthUseCase authUseCase) {
        super();
    }
}