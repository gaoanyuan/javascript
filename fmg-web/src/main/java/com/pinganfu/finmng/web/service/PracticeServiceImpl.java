package com.pinganfu.finmng.web.service;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * Created by anygao on 14-7-15.
 */
@Service
public class PracticeServiceImpl implements PracticeService {
    @Override
    public String getName() {
        SecurityContext context = SecurityContextHolder.getContext();
        return context.getAuthentication().getName();
    }
}
