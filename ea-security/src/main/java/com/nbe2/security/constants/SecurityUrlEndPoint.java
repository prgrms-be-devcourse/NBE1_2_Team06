package com.nbe2.security.constants;

import org.springframework.http.HttpMethod;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import com.nbe2.domain.user.UserRole;

@RequiredArgsConstructor
@Getter
public enum SecurityUrlEndPoint {

    // All
    HEALTH_CHECK_GET(HttpMethod.GET, "/health"),
    GUEST_OAUTH_POST(HttpMethod.POST, "/api/v1/oauth/**"),
    GUEST_AUTH_POST(HttpMethod.POST, "/api/v1/auth/**"),
    GUEST_NOTICES_GET(HttpMethod.GET, "/api/v1/notices/**"),
    GUEST_REVIEWS_GET(HttpMethod.GET, "/api/v1/reviews/**"),
    GUEST_EMERGENCY_DIRECTIONS_GET(HttpMethod.GET, "/api/v1/directions"),
    GUEST_EMERGENCY_ROOMS_GET(HttpMethod.GET, "/api/v1/emergency-rooms/**"),
    GUEST_POST_GET(HttpMethod.GET, "/api/v1/posts/**"),
    CHAT_POST(HttpMethod.POST, "/api/v1/chatbot/**"),
    CHAT_GET(HttpMethod.GET, "/api/v1/chatbot/**"),
    CHAT_DELETE(HttpMethod.DELETE, "/api/v1/chatbot/**"),

    // Medical Person
    MEDICAL_PERSON_NOTICES_PUT(HttpMethod.PUT, "/api/v1/notices", UserRole.MEDICAL_PERSON),
    MEDICAL_PERSON_NOTICES_POST(HttpMethod.POST, "/api/v1/notices", UserRole.MEDICAL_PERSON),
    MEDICAL_PERSON_NOTICES_DELETE(HttpMethod.POST, "/api/v1/notices", UserRole.MEDICAL_PERSON),

    // ADMIN
    ADMIN_PENDINGS_ALL(HttpMethod.POST, "/api/v1/auth/admin/pendings", UserRole.ADMIN),
    ADMIN_PENDINGS_GET(HttpMethod.GET, "/api/v1/auth/admin/pendings", UserRole.ADMIN);

    private HttpMethod method;
    private String url;
    private UserRole userRole;

    SecurityUrlEndPoint(HttpMethod method, String url) {
        this.method = method;
        this.url = url;
        this.userRole = null;
    }

    SecurityUrlEndPoint(HttpMethod method, String url, UserRole userRole) {
        this.method = method;
        this.url = url;
        this.userRole = userRole;
    }
}
