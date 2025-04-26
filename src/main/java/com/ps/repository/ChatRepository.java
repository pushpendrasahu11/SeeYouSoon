package com.ps.repository;

import com.ps.model.Chat;
import com.ps.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChatRepository extends JpaRepository<Chat,Integer> {

    public List<Chat> findByUsersId(Integer userId);
    // findByUserId error dega becauase in model we definded vairble "users"

    @Query(
            "select c from Chat c where :user Member of c.users and :reqUser Member of c.users"
    )
    public Chat findChatByUsersId(@Param("user") User user, @Param("reqUser") User reqUser);

}
