package bf.mfptps.appnomination.web.vm;

import bf.mfptps.appnomination.service.dto.AgentDTO;
import javax.validation.constraints.Size;

/**
 * View Model extending the AgentDTO, which is meant to be used in the agent management UI.
 */
public class ManagedAgentVM extends AgentDTO {

    public static final int PASSWORD_MIN_LENGTH = 4;

    public static final int PASSWORD_MAX_LENGTH = 100;

    @Size(min = PASSWORD_MIN_LENGTH, max = PASSWORD_MAX_LENGTH)
    private String password = "12345678";

    public ManagedAgentVM() {
        // Empty constructor needed for Jackson.
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ManagedAgentVM{" + super.toString() + "} ";
    }
}
