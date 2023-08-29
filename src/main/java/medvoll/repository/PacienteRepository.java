package medvoll.repository;

import io.micrometer.observation.ObservationFilter;
import medvoll.models.Paciente;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Optional<Paciente> findById(Long id);

    Page<Paciente> findAllByAtivoTrue(Pageable dados);
}
