package springmvc.controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.counting;
import static java.util.function.Function.identity;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/lotto")
public class LottoController {
	// �s�� lotto ���v����
	private List<Set<Integer>> lottos = new ArrayList<>();
	
	// lotto �D�e�� (Http method = GET)
	//@RequestMapping(value = {"/", "/welcome"}, method = {RequestMethod.GET, RequestMethod.POST})
	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String index(Model model) {
		if(lottos.size() > 0) {
		// Lotto �έp��ơA�C�@�Ӹ��X�X�{������
		Map<Integer, Long> stat = null;
		// �N�Ҧ����X�׶����@�� List<Integer> (�Q�� flapMap �N��Ʃ�᦬�� collect �_��)
		List<Integer> nums = lottos.stream().flatMap(lotto -> lotto.stream()).collect(toList());
		// �N��Ƴz�L groupingby ���� ���� Map<Integer, Long>
		stat = nums.stream()
				.collect(groupingBy(identity(), counting()));
		// �[�W�Ƨ�
		Map<Integer, Long> statAndSort = new LinkedHashMap<>();
		stat.entrySet().stream()
				.sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
				.forEachOrdered(e -> statAndSort.put(e.getKey(), e.getValue()));
		// �N�Ƨǫᵲ�G������ stat
		stat = statAndSort;
		model.addAttribute("stat", stat);
		}
		
		//�Ҧ� lotto ���X���
		model.addAttribute("lottos", lottos);

		return "lotto/index";
}
	
	// �s�W/���o�̷s�q���︹
	@PostMapping("/add")
	public String add(RedirectAttributes attr) {
		// �ֳz 539 �︹ (1~39 ��5�Ӥ����ƪ��Ʀr)
		Set<Integer> lotto = getLottoNumIntegers();
		// �N lotto ��Ʃ�J lottos ���X��
		lottos.add(lotto);
		// �N lotto ��ƶǻ��� /addOk ����G�� submit
		attr.addFlashAttribute("lotto", lotto); // ��Ƥ��|��ܦb���}�C
		//attr.addAttribute("lotto", lotto); // ��Ʒ|��ܦb���}�C
		return "redirect:addOk";
	}
	
	@GetMapping(value = {"/addOk", "/updateOk"})
	public String success() {
		return "lotto/success";
	}
	
	// �����X
	@PutMapping("/{index}")
	public String update(@PathVariable("index") int index, RedirectAttributes attr) {
		// �ֳz 539 �︹ (1~39 ��5�Ӥ����ƪ��Ʀr)
		Set<Integer> lotto = getLottoNumIntegers();
		// �����X
		lottos.set(index, lotto);
		// �����^�쭺��
		return "redirect:./";
		/*
		// �N lotto ��ƶǻ��� /updateOk ����G�� submit
		attr.addFlashAttribute("lotto", lotto);
		//attr.addAttribute("lotto", lotto);
		return "redirect:../updateOk";
		*/
	}
	
	// �R�����X
	@DeleteMapping("/{index}")
	public String delete(@PathVariable("index") int index) {
		// �������X
		lottos.remove(index);
		return "redirect:./";
	}
	
	private Set<Integer> getLottoNumIntegers() {
		Random r = new Random();
		Set<Integer> lotto = new LinkedHashSet<>();
		while(lotto.size() < 5) {
			lotto.add(r.nextInt(39) + 1);
		}
		return lotto;
	}
	
	
	
}