package hexlet.code.service;

import hexlet.code.dto.LabelRequest;
import hexlet.code.dto.LabelResponse;
import hexlet.code.mapper.LabelMapper;
import hexlet.code.model.Label;
import hexlet.code.repository.LabelRepository;
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

    public void deleteById(Long id) {
        labelRepository.deleteById(id);
    }
}
