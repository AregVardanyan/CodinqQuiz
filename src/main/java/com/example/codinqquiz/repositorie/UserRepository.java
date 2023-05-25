package com.example.codinqquiz.repositorie;

import com.example.codinqquiz.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    boolean existsByEmailAndNickname(String email, String nickname);

    boolean existsByEmail(String email);
    boolean existsByNickname(String nickname);
    Optional<User> findByEmail(String email);

    Optional<User> findByNickname(String nickname);

    boolean existsByIdAndVerificationCode(int id, String verificationCode);

    @Transactional
    @Modifying
    @Query(value = "update User u set u.verificationCode=:verifyCode where u.id=:id")
    void updateVerificationCode(@Param("id") int id,
                                @Param("verifyCode") String verificationCode);

}
