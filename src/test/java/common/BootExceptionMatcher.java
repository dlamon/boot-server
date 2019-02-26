package common;

import com.example.boot.server.exception.BootException;
import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * @author LiaoWei
 */
public class BootExceptionMatcher extends TypeSafeMatcher<BootException> {
    private final String code;

    public BootExceptionMatcher(String code) {
        this.code = code;
    }

    @Override
    protected boolean matchesSafely(BootException item) {
        return code.equals(item.getCode());
    }

    @Override
    public void describeTo(Description description) {
        description.appendValue(new BootException(code));
    }
}
