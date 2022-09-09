package com.olakunle.myspringsecurity.security;

import java.util.HashSet;
import java.util.Set;

import static com.olakunle.myspringsecurity.security.ApplicationUserPermission.*;

public enum ApplicationUserRole {

    STUDENT(Set.of()),
    ADMIN(Set.of(COURSE_READ, COURSE_WRITE, STUDENT_WRITE, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<ApplicationUserPermission> getPermissions() {
        return permissions;
    }
}
