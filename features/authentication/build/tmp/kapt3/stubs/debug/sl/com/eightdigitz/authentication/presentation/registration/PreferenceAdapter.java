package sl.com.eightdigitz.authentication.presentation.registration;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0016B-\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\u0002\u0010\nJ\b\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u00020\t2\n\u0010\u0010\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u001c\u0010\u0012\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u000eH\u0016R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0017"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/registration/PreferenceAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lsl/com/eightdigitz/authentication/presentation/registration/PreferenceAdapter$ViewHolder;", "preferenceList", "", "Lsl/com/eightdigitz/core/model/domain/DPreference;", "onClickPreference", "Lkotlin/Function2;", "", "", "(Ljava/util/List;Lkotlin/jvm/functions/Function2;)V", "getOnClickPreference", "()Lkotlin/jvm/functions/Function2;", "getItemCount", "", "onBindViewHolder", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "ViewHolder", "authentication_debug"})
public final class PreferenceAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<sl.com.eightdigitz.authentication.presentation.registration.PreferenceAdapter.ViewHolder> {
    private final java.util.List<sl.com.eightdigitz.core.model.domain.DPreference> preferenceList = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.jvm.functions.Function2<sl.com.eightdigitz.core.model.domain.DPreference, java.lang.Boolean, kotlin.Unit> onClickPreference = null;
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public sl.com.eightdigitz.authentication.presentation.registration.PreferenceAdapter.ViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    sl.com.eightdigitz.authentication.presentation.registration.PreferenceAdapter.ViewHolder holder, int position) {
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.jvm.functions.Function2<sl.com.eightdigitz.core.model.domain.DPreference, java.lang.Boolean, kotlin.Unit> getOnClickPreference() {
        return null;
    }
    
    public PreferenceAdapter(@org.jetbrains.annotations.NotNull()
    java.util.List<sl.com.eightdigitz.core.model.domain.DPreference> preferenceList, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super sl.com.eightdigitz.core.model.domain.DPreference, ? super java.lang.Boolean, kotlin.Unit> onClickPreference) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lsl/com/eightdigitz/authentication/presentation/registration/PreferenceAdapter$ViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lsl/com/eightdigitz/authentication/presentation/registration/PreferenceAdapter;Landroid/view/View;)V", "onBind", "", "position", "", "authentication_debug"})
    public final class ViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        public final void onBind(int position) {
        }
        
        public ViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}