package net.floppy.springboot.Repository;


import net.floppy.springboot.Entity.Wikimedia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WikimediaRepo extends JpaRepository<Wikimedia, Long> {
}
