package cz.etnetera.reesmo.model.form.notifier;

import cz.etnetera.reesmo.notifier.EmailNotifier;
import cz.etnetera.reesmo.notifier.Notifier2;
import cz.etnetera.reesmo.notifier.URLNotifier;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NotifierCommand {

    private String id;

    private boolean enabled;

    private String addresses;

    public NotifierCommand(Notifier2 notifier) {
        this.id = notifier.getId();
        this.enabled = notifier.isEnabled();
        if (notifier instanceof EmailNotifier){
            EmailNotifier emailNotifier = (EmailNotifier) notifier;
            this.addresses = emailNotifier.getAddresses().stream().collect(Collectors.joining(";"));
        }
        if (notifier instanceof URLNotifier){
            URLNotifier urlNotifier = (URLNotifier) notifier;
            this.addresses = urlNotifier.getAddresses().stream().collect(Collectors.joining(";"));
        }
    }

    public NotifierCommand() {
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getAddresses() {
        return addresses;
    }

    public void setAddresses(String addresses) {
        this.addresses = addresses;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void toNotifier(Notifier2 notifier) {
        notifier.setEnabled(isEnabled());
        if (notifier instanceof EmailNotifier){
            EmailNotifier emailNotifier = (EmailNotifier) notifier;
            emailNotifier.setAddresses(Arrays.asList(getAddresses().split(";")));
        }
        if (notifier instanceof URLNotifier){
            URLNotifier urlNotifier = (URLNotifier) notifier;
            urlNotifier.setAddresses(Arrays.asList(getAddresses().split(";")));
        }


    }
}
