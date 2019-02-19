package common;

import com.example.boot.server.exception.BootException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author LiaoWei
 */
public class BootExceptionMatcher extends TypeSafeMatcher<BootException> {
    private String code;

    public BootExceptionMatcher(String code) {
        this.code = code;
    }

    @Override
    protected boolean matchesSafely(BootException item) {
        if (code.equals(item.getCode())) {
            return true;
        }
        return false;
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(new BootException(code));
    }
}
