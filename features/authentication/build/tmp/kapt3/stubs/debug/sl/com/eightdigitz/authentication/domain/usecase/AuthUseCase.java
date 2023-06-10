package sl.com.eightdigitz.authentication.domain.usecase;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000x\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tJ\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00062\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\u000fJ\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\u00062\u0006\u0010\u0012\u001a\u00020\u0013J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00062\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u0013J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00180\u0006J\u0014\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u001b\u001a\u00020\u0013J\u0014\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u00062\u0006\u0010\u001e\u001a\u00020\u001fJ\u0014\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\u00062\u0006\u0010\b\u001a\u00020\"J\u0014\u0010#\u001a\b\u0012\u0004\u0012\u00020$0\u00062\u0006\u0010%\u001a\u00020\u0013J\u0014\u0010&\u001a\b\u0012\u0004\u0012\u00020\u00130\u00062\u0006\u0010\'\u001a\u00020(R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006)"}, d2 = {"Lsl/com/eightdigitz/authentication/domain/usecase/AuthUseCase;", "", "authRepository", "Lsl/com/eightdigitz/authentication/domain/repository/AuthRepository;", "(Lsl/com/eightdigitz/authentication/domain/repository/AuthRepository;)V", "addUserPreference", "Lio/reactivex/Single;", "Lsl/com/eightdigitz/core/model/domain/DUser;", "request", "Lsl/com/eightdigitz/client/apiSupports/requests/AddUserPreferenceRequest;", "contactUs", "Lsl/com/eightdigitz/core/model/domain/DContactUs;", "contactUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/ContactUsRequest;", "createAccount", "Lsl/com/eightdigitz/client/apiSupports/requests/RegisterRequest;", "getOTP", "Lsl/com/eightdigitz/core/model/domain/DOTP;", "phoneNumber", "", "getOTPToken", "Lsl/com/eightdigitz/core/model/domain/DOTPToken;", "otp", "getPreferences", "Lsl/com/eightdigitz/core/model/ListResponse;", "Lsl/com/eightdigitz/core/model/domain/DPreference;", "getUserByIDToken", "idToken", "joinUs", "Lsl/com/eightdigitz/core/model/domain/DJoinUs;", "joinUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/JoinUsRequest;", "registerFirebaseToken", "Lsl/com/eightdigitz/core/model/domain/DFirebaseToken;", "Lsl/com/eightdigitz/client/apiSupports/models/FirebaseToken;", "requestWelcomeNotification", "Lsl/com/eightdigitz/models/Success;", "userId", "uploadAvatar", "image", "Lokhttp3/MultipartBody$Part;", "authentication_debug"})
public final class AuthUseCase {
    private final sl.com.eightdigitz.authentication.domain.repository.AuthRepository authRepository = null;
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<java.lang.String> uploadAvatar(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part image) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DContactUs> contactUs(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest contactUsRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DJoinUs> joinUs(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest joinUsRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DOTP> getOTP(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DOTPToken> getOTPToken(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String otp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> getUserByIDToken(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.ListResponse<sl.com.eightdigitz.core.model.domain.DPreference>> getPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DFirebaseToken> registerFirebaseToken(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.models.FirebaseToken request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> createAccount(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> addUserPreference(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final io.reactivex.Single<sl.com.eightdigitz.models.Success> requestWelcomeNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    public AuthUseCase(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.domain.repository.AuthRepository authRepository) {
        super();
    }
}