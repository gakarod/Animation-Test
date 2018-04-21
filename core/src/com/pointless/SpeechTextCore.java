package com.pointless;


public interface SpeechTextCore {
    void promptSpeechInput();
    void showToast(CharSequence toastMessage, int toastDuration);
    void checkRecord();
}
