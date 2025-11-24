package com.example.soft_backend.domain.user.entity;

public enum CoopStyle {

    SC("계획을 꼼꼼히 설계자"),     // 예: 구조↑ / 소통↑
    SE("유연한 흐름 조율가"),       // 예시 이름, 원하는 말로 바꿔
    FC("배려 많은 실행가"),
    FL("마감 전에 몰아서 터뜨리는 편");  // 그냥 예시

    private final String label;

    CoopStyle(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
