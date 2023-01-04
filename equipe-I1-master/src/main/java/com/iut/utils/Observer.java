package com.iut.utils;

public interface Observer {
        public void update(AbstractSubject subj);

        public void update(AbstractSubject subj, Object data);
}
