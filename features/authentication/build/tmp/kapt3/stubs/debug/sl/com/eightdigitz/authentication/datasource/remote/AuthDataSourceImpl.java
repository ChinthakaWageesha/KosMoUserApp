package sl.com.eightdigitz.authentication.datasource.remote;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u00aa\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B5\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0016\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u00102\u0006\u0010\u0016\u001a\u00020\u0017H\u0016J\u0016\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\u0016\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00102\u0006\u0010\u001d\u001a\u00020\u001eH\u0016J\u001e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020 0\u00102\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010!\u001a\u00020\u001eH\u0016J\u0014\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020$0#0\u0010H\u0016J\u0016\u0010%\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010&\u001a\u00020\u001eH\u0016J\u0016\u0010\'\u001a\b\u0012\u0004\u0012\u00020(0\u00102\u0006\u0010)\u001a\u00020*H\u0016J\u0016\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u00102\u0006\u0010-\u001a\u00020.H\u0016J\u0016\u0010/\u001a\b\u0012\u0004\u0012\u0002000\u00102\u0006\u00101\u001a\u00020\u001eH\u0016J\u0010\u00102\u001a\u0002032\u0006\u00104\u001a\u00020 H\u0002J\u0012\u00105\u001a\u0002032\b\u00106\u001a\u0004\u0018\u000107H\u0002J\u0016\u00108\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00102\u0006\u00109\u001a\u00020:H\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006;"}, d2 = {"Lsl/com/eightdigitz/authentication/datasource/remote/AuthDataSourceImpl;", "Lsl/com/eightdigitz/authentication/data/datasource/AuthDataSource;", "mSharedPreferences", "Landroid/content/SharedPreferences;", "authApi", "Lsl/com/eightdigitz/client/apis/AuthApi;", "joinContactApi", "Lsl/com/eightdigitz/client/apis/JoinContactApi;", "multimediaApi", "Lsl/com/eightdigitz/client/apis/MultimediaApi;", "preferencesApi", "Lsl/com/eightdigitz/client/apis/PreferencesApi;", "notificationApi", "Lsl/com/eightdigitz/client/apis/NotificationApi;", "(Landroid/content/SharedPreferences;Lsl/com/eightdigitz/client/apis/AuthApi;Lsl/com/eightdigitz/client/apis/JoinContactApi;Lsl/com/eightdigitz/client/apis/MultimediaApi;Lsl/com/eightdigitz/client/apis/PreferencesApi;Lsl/com/eightdigitz/client/apis/NotificationApi;)V", "addUserPreference", "Lio/reactivex/Single;", "Lsl/com/eightdigitz/core/model/domain/DUser;", "addUserPreferenceRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/AddUserPreferenceRequest;", "contactSupport", "Lsl/com/eightdigitz/core/model/domain/DContactUs;", "contactUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/ContactUsRequest;", "createAccount", "registerRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/RegisterRequest;", "getOTP", "Lsl/com/eightdigitz/core/model/domain/DOTP;", "phoneNumber", "", "getOTPToken", "Lsl/com/eightdigitz/core/model/domain/DOTPToken;", "otp", "getPreferences", "Lsl/com/eightdigitz/core/model/ListResponse;", "Lsl/com/eightdigitz/core/model/domain/DPreference;", "getUserByIDToken", "idToken", "joinUs", "Lsl/com/eightdigitz/core/model/domain/DJoinUs;", "joinUsRequest", "Lsl/com/eightdigitz/client/apiSupports/requests/JoinUsRequest;", "registerFirebaseToken", "Lsl/com/eightdigitz/core/model/domain/DFirebaseToken;", "request", "Lsl/com/eightdigitz/client/apiSupports/models/FirebaseToken;", "requestWelcomeNotification", "Lsl/com/eightdigitz/models/Success;", "userId", "saveTokens", "", "otpToken", "saveUser", "mUser", "Lsl/com/eightdigitz/client/apiSupports/models/User;", "uploadAvatar", "image", "Lokhttp3/MultipartBody$Part;", "authentication_debug"})
public final class AuthDataSourceImpl implements sl.com.eightdigitz.authentication.data.datasource.AuthDataSource {
    private final android.content.SharedPreferences mSharedPreferences = null;
    private final sl.com.eightdigitz.client.apis.AuthApi authApi = null;
    private final sl.com.eightdigitz.client.apis.JoinContactApi joinContactApi = null;
    private final sl.com.eightdigitz.client.apis.MultimediaApi multimediaApi = null;
    private final sl.com.eightdigitz.client.apis.PreferencesApi preferencesApi = null;
    private final sl.com.eightdigitz.client.apis.NotificationApi notificationApi = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<java.lang.String> uploadAvatar(@org.jetbrains.annotations.NotNull()
    okhttp3.MultipartBody.Part image) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DContactUs> contactSupport(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.ContactUsRequest contactUsRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DJoinUs> joinUs(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.JoinUsRequest joinUsRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DOTP> getOTP(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DOTPToken> getOTPToken(@org.jetbrains.annotations.NotNull()
    java.lang.String phoneNumber, @org.jetbrains.annotations.NotNull()
    java.lang.String otp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> getUserByIDToken(@org.jetbrains.annotations.NotNull()
    java.lang.String idToken) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.ListResponse<sl.com.eightdigitz.core.model.domain.DPreference>> getPreferences() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DFirebaseToken> registerFirebaseToken(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.models.FirebaseToken request) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> createAccount(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.RegisterRequest registerRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.core.model.domain.DUser> addUserPreference(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apiSupports.requests.AddUserPreferenceRequest addUserPreferenceRequest) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public io.reactivex.Single<sl.com.eightdigitz.models.Success> requestWelcomeNotification(@org.jetbrains.annotations.NotNull()
    java.lang.String userId) {
        return null;
    }
    
    private final void saveTokens(sl.com.eightdigitz.core.model.domain.DOTPToken otpToken) {
    }
    
    private final void saveUser(sl.com.eightdigitz.client.apiSupports.models.User mUser) {
    }
    
    public AuthDataSourceImpl(@org.jetbrains.annotations.NotNull()
    android.content.SharedPreferences mSharedPreferences, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apis.AuthApi authApi, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apis.JoinContactApi joinContactApi, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apis.MultimediaApi multimediaApi, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apis.PreferencesApi preferencesApi, @org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.client.apis.NotificationApi notificationApi) {
        super();
    }
}