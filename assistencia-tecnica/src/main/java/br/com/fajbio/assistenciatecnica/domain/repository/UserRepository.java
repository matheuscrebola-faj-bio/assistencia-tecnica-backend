package br.com.fajbio.assistenciatecnica.domain.repository;

import br.com.fajbio.assistenciatecnica.api.dto.UserRes;
import br.com.fajbio.assistenciatecnica.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query(
            value = "SELECT id, username, email, roles FROM assistencia_tecnica.tb_users",
            nativeQuery = true
    )
    List<UserRes> encontrarTodos();

    User findByEmail(String email);
}
