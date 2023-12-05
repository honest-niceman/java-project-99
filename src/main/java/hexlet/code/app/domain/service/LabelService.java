package hexlet.code.app.domain.service;

import hexlet.code.app.domain.dtos.LabelRequest;
import hexlet.code.app.domain.dtos.LabelResponse;
import hexlet.code.app.domain.mappers.LabelMapper;
import hexlet.code.app.domain.model.Label;
import hexlet.code.app.domain.repository.LabelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class LabelService {

    private final LabelRepository labelRepository;
    private final LabelMapper labelMapper;

    public LabelResponse findById(Long id) {
        Label label = labelRepository.findById(id).orElseThrow();
        return labelMapper.toLabelResponse(label);
    }

    public List<LabelResponse> findAll() {
        return labelRepository.findAll().stream().map(labelMapper::toLabelResponse).toList();
    }

    public LabelResponse save(LabelRequest labelRequest) {
        Label label = labelMapper.toEntity(labelRequest);
        Label saved = labelRepository.save(label);
        return labelMapper.toLabelResponse(saved);
    }

    public LabelResponse updateById(Long id, LabelRequest labelRequest) {
        Label label = labelRepository.findById(id).orElseThrow();
        Label updated = labelMapper.partialUpdate(labelRequest, label);
        Label saved = labelRepository.save(updated);
        return labelMapper.toLabelResponse(saved);
    }

    public long count() {
        return labelRepository.count();
    }

    public void deleteById(Long id) {
        labelRepository.deleteById(id);
    }
}
