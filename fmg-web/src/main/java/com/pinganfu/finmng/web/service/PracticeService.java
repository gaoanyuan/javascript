package com.pinganfu.finmng.web.service;

import org.springframework.security.access.annotation.Secured;

/**
 * Created by anygao on 14-7-15.
 */
public interface PracticeService {
    @Secured("GROUP_ADMIN")
    String getName();
}
