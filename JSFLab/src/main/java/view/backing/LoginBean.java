
package view.backing;

import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlSelectBooleanCheckbox;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * @author Monika
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean {

    private String username;
    private String password;
    private HtmlSelectBooleanCheckbox acceptCheckbox;
    private HtmlCommandButton loginButton;

    /**
     * Creates a new instance of LoginBean
     */
    public LoginBean() {
    }

    public String login() {
        if (isOracleUser()) return null;
        if (username.equals(password)) {
            return "success";
        } else {
            return "failure";
        }
    }

    public void activateButton(ValueChangeEvent e) {
        if (acceptCheckbox.isSelected())
            loginButton.setDisabled(false);
        else
            loginButton.setDisabled(true);

        FacesContext context = FacesContext.getCurrentInstance();
        context.renderResponse();
    }

    public boolean isOracleUser() {
        if ("scott".equals(username) && "tiger".equals(password)) {
            FacesContext context = FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    ResourceBundle.getBundle("ApplicationMessages").getString("validation.oracle"), null);
            context.addMessage(null, message);
            return true;
        }
        return false;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public HtmlSelectBooleanCheckbox getAcceptCheckbox() {
        return acceptCheckbox;
    }

    public void setAcceptCheckbox(HtmlSelectBooleanCheckbox acceptCheckbox) {
        this.acceptCheckbox = acceptCheckbox;
    }

    public HtmlCommandButton getLoginButton() {
        return loginButton;
    }

    public void setLoginButton(HtmlCommandButton loginButton) {
        this.loginButton = loginButton;
    }
}
