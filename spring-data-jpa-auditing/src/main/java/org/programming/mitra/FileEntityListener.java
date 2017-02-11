package org.programming.mitra;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.transaction.Transactional;

import static javax.transaction.Transactional.TxType.MANDATORY;
import static org.programming.mitra.Action.*;

/**
 * @author Naresh Joshi
 */

public class FileEntityListener {

    @PrePersist
    public void prePersist(File target) {
        perform(target, INSERTED);
    }

    @PreUpdate
    public void preUpdate(File target) {
        perform(target, UPDATED);
    }

    @PreRemove
    public void preRemove(File target) {
        perform(target, DELETED);
    }

    @Transactional(MANDATORY)
    private void perform(File target, Action action) {
        FileHistoryRepository fileHistoryRepository = BeanUtil.getBean(FileHistoryRepository.class);
        fileHistoryRepository.save(new FileHistory(target, action));
    }

}
