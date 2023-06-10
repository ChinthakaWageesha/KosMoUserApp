package sl.com.eightdigitz.presentation;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u0082\u0001\u0003\u0006\u0007\b\u00a8\u0006\t"}, d2 = {"Lsl/com/eightdigitz/presentation/ResourceState;", "", "()V", "ERROR", "LOADING", "SUCCESS", "Lsl/com/eightdigitz/presentation/ResourceState$LOADING;", "Lsl/com/eightdigitz/presentation/ResourceState$SUCCESS;", "Lsl/com/eightdigitz/presentation/ResourceState$ERROR;", "presentation_debug"})
public abstract class ResourceState {
    
    private ResourceState() {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lsl/com/eightdigitz/presentation/ResourceState$LOADING;", "Lsl/com/eightdigitz/presentation/ResourceState;", "()V", "presentation_debug"})
    public static final class LOADING extends sl.com.eightdigitz.presentation.ResourceState {
        public static final sl.com.eightdigitz.presentation.ResourceState.LOADING INSTANCE = null;
        
        private LOADING() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lsl/com/eightdigitz/presentation/ResourceState$SUCCESS;", "Lsl/com/eightdigitz/presentation/ResourceState;", "()V", "presentation_debug"})
    public static final class SUCCESS extends sl.com.eightdigitz.presentation.ResourceState {
        public static final sl.com.eightdigitz.presentation.ResourceState.SUCCESS INSTANCE = null;
        
        private SUCCESS() {
            super();
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 16}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002\u00a8\u0006\u0003"}, d2 = {"Lsl/com/eightdigitz/presentation/ResourceState$ERROR;", "Lsl/com/eightdigitz/presentation/ResourceState;", "()V", "presentation_debug"})
    public static final class ERROR extends sl.com.eightdigitz.presentation.ResourceState {
        public static final sl.com.eightdigitz.presentation.ResourceState.ERROR INSTANCE = null;
        
        private ERROR() {
            super();
        }
    }
}