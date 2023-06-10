package sl.com.eightdigitz.authentication.data.datasource;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&J\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u00032\u0006\u0010\t\u001a\u00020\nH&J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0016\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\u00032\u0006\u0010\u0010\u001a\u00020\u0011H&J\u001e\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00130\u00032\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0011H&J\u0014\u0010\u0015\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00170\u00160\u0003H&J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0019\u001a\u00020\u0011H&J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00032\u0006\u0010\u001c\u001a\u00020\u001dH&J\u0016\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u001f0\u00032\u0006\u0010 \u001a\u00020!H&J\u0016\u0010\"\u001a\b\u0012\u0004\u0012\u00020#0\u00032\u0006\u0010$\u001a\u00020\u0011H&J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u00032\u0006\u0010&\u001a\u00020\'H&\u00a8\u0006("}, d2 = {"Lsl/com/eightdigitz/authentication/data/datasource/AuthDataSource;", "", "addUserPreference", "Lio/reactivex/Single;", "Lsl/com/eightdigitz/core/model/domain/DUser;", "addUserPreferenceRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/AddUserPreferenceRequest;", "contactSupport", "Lsl/com/eightdigitz/core/model/domain/DContactUs;", "contactUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/ContactUsRequest;", "createAccount", "registerRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/RegisterRequest;", "getOTP", "Lsl/com/eightdigitz/core/model/domain/DOTP;", "phoneNumber", "", "getOTPToken", "Lsl/com/eightdigitz/core/model/domain/DOTPToken;", "otp", "getPreferences", "Lsl/com/eightdigitz/core/model/ListResponse;", "Lsl/com/eightdigitz/core/model/domain/DPreference;", "getUserByIDToken", "idToken", "joinUs", "Lsl/com/eightdigitz/core/model/domain/DJoinUs;", "joinUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/JoinUsRequest;", "registerFirebaseToken", "Lsl/com/eightdigitz/core/model/domain/DFirebaseToken;", "request", "Lsl/com/eightdigitz/client/apiSupports/models/FirebaseToken;", "requestWelcomeNotification", "Lsl/com/eightdigitz/models/Success;", "userId", "uploadAvatar", "image", "Lokhttp3/MultipartBody$Part;", "authentication_debug"})
public abstract interface AuthDataSource {
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<java.lang.String> uploadAvatar(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part image);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DContactUs> contactSupport(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest contactUsRequest);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DJoinUs> joinUs(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest joinUsRequest);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DOTP> getOTP(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DOTPToken> getOTPToken(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String otp);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> getUserByIDToken(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.ListResponse<sl.com.eightdigitz.core.model.domain.DPreference>> getPreferences();
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DFirebaseToken> registerFirebaseToken(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.models.FirebaseToken request);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> createAccount(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest registerRequest);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> addUserPreference(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest addUserPreferenceRequest);
    
    @org.jetbrains.annotations.NotNull()
    public abstract io.reactivex.Single<sl.com.eightdigitz.models.Success> requestWelcomeNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String userId);
}