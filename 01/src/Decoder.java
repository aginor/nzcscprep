import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.jasypt.exceptions.EncryptionInitializationException;
import org.jasypt.exceptions.EncryptionOperationNotPossibleException;
import org.jasypt.iv.IvGenerator;
import org.jasypt.iv.NoIvGenerator;
import org.jasypt.iv.RandomIvGenerator;
import org.jasypt.salt.RandomSaltGenerator;
import org.jasypt.salt.SaltGenerator;
import org.jasypt.salt.ZeroSaltGenerator;
import org.jasypt.util.text.AES256TextEncryptor;
import org.jasypt.util.text.BasicTextEncryptor;
import org.jasypt.util.text.StrongTextEncryptor;
import org.jasypt.util.text.TextEncryptor;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.CharBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class Decoder {


    static CharsetEncoder asciiCharset = Charset.forName("US-ASCII").newEncoder();


     public static void main(String[] args) {

         String[] algos = {
                 "PBEWITHMD5ANDDES",
                 "PBEWITHMD5ANDTRIPLEDES",
                 "PBEWITHSHA1ANDDESEDE",
            };


         String text = "Ernest Rutherford\n" +
                 "From Wikipedia, the free encyclopedia\n" +
                 "Jump to navigation\n" +
                 "Jump to search\n" +
                 "\"Lord Rutherford\" redirects here. It is not to be confused with Lord Rutherfurd or Andrew Rutherford, 1st Earl of Teviot.\n" +
                 "The Right Honourable\n" +
                 "\n" +
                 "The Lord Rutherford of Nelson\n" +
                 "\n" +
                 "OM PRS HonFRSE\n" +
                 "Ernest Rutherford LOC.jpg\n" +
                 "Ernest Rutherford\n" +
                 "President of the Royal Society\n" +
                 "In office\n" +
                 "1925–1930\n" +
                 "Preceded by\tSir Charles Scott Sherrington\n" +
                 "Succeeded by\tSir Frederick Gowland Hopkins\n" +
                 "Personal details\n" +
                 "Born\t30 August 1871\n" +
                 "Brightwater, Colony of New Zealand\n" +
                 "Died\t19 October 1937 (aged 66)\n" +
                 "Cambridge, England\n" +
                 "Citizenship\tBritish subject, New Zealand\n" +
                 "Residence\tNew Zealand, United Kingdom\n" +
                 "Signature\t\n" +
                 "Alma mater\tUniversity of New Zealand\n" +
                 "Cavendish Laboratory, University of Cambridge\n" +
                 "Known for\t\n" +
                 "\n" +
                 "    Discovery of alpha and beta radioactivity\n" +
                 "    Discovery of atomic nucleus (Rutherford model)\n" +
                 "    Rutherford scattering\n" +
                 "    Rutherford backscattering spectroscopy\n" +
                 "    Discovery of proton\n" +
                 "    Rutherford (unit)\n" +
                 "    Coining the term 'artificial disintegration'\n" +
                 "\n" +
                 "Awards\t\n" +
                 "\n" +
                 "    Rumford Medal (1904)\n" +
                 "    Nobel Prize in Chemistry (1908)\n" +
                 "    Barnard Medal (1910)\n" +
                 "    Elliott Cresson Medal (1910)\n" +
                 "    Foreign Associate of the National Academy of Sciences (1911)\n" +
                 "    Matteucci Medal (1913)\n" +
                 "    Hector Memorial Medal (1916)\n" +
                 "    Dalton Medal (1919)\n" +
                 "    Copley Medal (1922)\n" +
                 "    Franklin Medal (1924)\n" +
                 "    Albert Medal (1928)\n" +
                 "    Faraday Medal (1930)\n" +
                 "    Wilhelm Exner Medal (1936)\n" +
                 "    Faraday Lectureship Prize (1936)\n" +
                 "\n" +
                 "Scientific career\n" +
                 "Fields\tPhysics and chemistry\n" +
                 "Institutions\t\n" +
                 "\n" +
                 "    McGill University\n" +
                 "    University of Manchester\n" +
                 "    University of Cambridge\n" +
                 "\n" +
                 "Academic advisors\t\n" +
                 "\n" +
                 "    Alexander Bickerton\n" +
                 "    J. J. Thomson[1]\n" +
                 "\n" +
                 "Doctoral students\t\n" +
                 "\n" +
                 "    Nazir Ahmed\n" +
                 "    Norman Alexander\n" +
                 "    Edward Victor Appleton\n" +
                 "    Robert William Boyle\n" +
                 "    James Chadwick\n" +
                 "    Rafi Muhammad Chaudhry\n" +
                 "    Norman Feather\n" +
                 "    Daulat Singh Kothari\n" +
                 "    Alexander McAulay\n" +
                 "    Cecil Powell\n" +
                 "    Henry DeWolf Smyth\n" +
                 "    Ernest Walton\n" +
                 "    Evan James Williams\n" +
                 "    C. E. Wynn-Williams\n" +
                 "    Yulii Borisovich Khariton\n" +
                 "\n" +
                 "Other notable students\t\n" +
                 "\n" +
                 "    Edward Andrade\n" +
                 "    Patrick Blackett\n" +
                 "    Niels Bohr\n" +
                 "    Bertram Boltwood\n" +
                 "    Harriet Brooks\n" +
                 "    Teddy Bullard\n" +
                 "    John Cockcroft\n" +
                 "    Charles Galton Darwin\n" +
                 "    Charles Drummond Ellis\n" +
                 "    Kazimierz Fajans\n" +
                 "    Hans Geiger\n" +
                 "    Otto Hahn\n" +
                 "    Douglas Hartree\n" +
                 "    Pyotr Kapitsa\n" +
                 "    George Laurence\n" +
                 "    Iven Mackay\n" +
                 "    Ernest Marsden\n" +
                 "    Mark Oliphant\n" +
                 "    Thomas Royds\n" +
                 "    Frederick Soddy\n" +
                 "\n" +
                 "Influenced\t\n" +
                 "\n" +
                 "    Henry Moseley\n" +
                 "    Hans Geiger\n" +
                 "    Albert Beaumont Wood\n" +
                 "\n" +
                 "Ernest Rutherford, 1st Baron Rutherford of Nelson, OM, FRS, HonFRSE[2] (30 August 1871 – 19 October 1937) was a New Zealand-born British physicist who came to be known as the father of nuclear physics.[3] Encyclopædia Britannica considers him to be the greatest experimentalist since Michael Faraday (1791–1867).[3] He also spent a substantial amount of his career abroad, in both Canada and the United Kingdom.\n" +
                 "\n" +
                 "In early work, Rutherford discovered the concept of radioactive half-life, the radioactive element radon,[4] and differentiated and named alpha and beta radiation.[5] This work was performed at McGill University in Montreal, Quebec, Canada. It is the basis for the Nobel Prize in Chemistry he was awarded in 1908 \"for his investigations into the disintegration of the elements, and the chemistry of radioactive substances\",[6] for which he was the first Oceanian Nobel laureate, and the first to perform the awarded work in Canada.\n" +
                 "\n" +
                 "Rutherford moved in 1907 to the Victoria University of Manchester (today University of Manchester) in the UK, where he and Thomas Royds proved that alpha radiation is helium nuclei.[7][8] Rutherford performed his most famous work after he became a Nobel laureate.[6] In 1911, although he could not prove that it was positive or negative,[9] he theorized that atoms have their charge concentrated in a very small nucleus,[10] and thereby pioneered the Rutherford model of the atom, through his discovery and interpretation of Rutherford scattering by the gold foil experiment of Hans Geiger and Ernest Marsden. He performed the first artificially induced nuclear reaction in 1917 in experiments where nitrogen nuclei were bombarded with alpha particles. As a result, he discovered the emission of a subatomic particle which, in 1919, he called the \"hydrogen atom\" but, in 1920, he more accurately named the proton.[11][12]\n" +
                 "\n" +
                 "Rutherford became Director of the Cavendish Laboratory at the University of Cambridge in 1919. Under his leadership the neutron was discovered by James Chadwick in 1932 and in the same year the first experiment to split the nucleus in a fully controlled manner was performed by students working under his direction, John Cockcroft and Ernest Walton. After his death in 1937, he was buried in Westminster Abbey near Sir Isaac Newton. The chemical element rutherfordium (element 104) was named after him in 1997.\n" +
                 "Contents\n" +
                 "\n" +
                 "    1 Biography\n" +
                 "        1.1 Early life and education\n" +
                 "        1.2 Later years and honours\n" +
                 "    2 Scientific research\n" +
                 "        2.1 Gold foil experiment\n" +
                 "    3 Legacy\n" +
                 "        3.1 Nuclear physics\n" +
                 "        3.2 Items named in honour of Rutherford's life and work\n" +
                 "    4 Publications\n" +
                 "        4.1 Articles\n" +
                 "    5 Arms\n" +
                 "    6 See also\n" +
                 "    7 References\n" +
                 "    8 Further reading\n" +
                 "    9 External links\n" +
                 "\n" +
                 "Biography\n" +
                 "Early life and education\n" +
                 "\n" +
                 "Ernest Rutherford was the son of James Rutherford, a farmer, and his wife Martha Thompson, originally from Hornchurch, Essex, England.[13] James had emigrated to New Zealand from Perth, Scotland, \"to raise a little flax and a lot of children\". Ernest was born at Brightwater, near Nelson, New Zealand. His first name was mistakenly spelled 'Earnest' when his birth was registered.[14] Rutherford's mother Martha Thompson was a schoolteacher.[15]\n" +
                 "Rutherford in 1892, aged 21\n" +
                 "\n" +
                 "He studied at Havelock School and then Nelson College and won a scholarship to study at Canterbury College, University of New Zealand, where he participated in the debating society and played rugby.[16] After gaining his BA, MA and BSc, and doing two years of research during which he invented a new form of radio receiver, in 1895 Rutherford was awarded an 1851 Research Fellowship from the Royal Commission for the Exhibition of 1851,[17] to travel to England for postgraduate study at the Cavendish Laboratory, University of Cambridge.[18] He was among the first of the 'aliens' (those without a Cambridge degree) allowed to do research at the university, under the leadership of J. J. Thomson,[1] which aroused jealousies from the more conservative members of the Cavendish fraternity. With Thomson's encouragement, he managed to detect radio waves at half a mile and briefly held the world record for the distance over which electromagnetic waves could be detected, though when he presented his results at the British Association meeting in 1896, he discovered he had been outdone[further explanation needed] by Guglielmo Marconi, who was also lecturing.\n" +
                 "\n" +
                 "In 1898, Thomson recommended Rutherford for a position at McGill University in Montreal, Canada. He was to replace Hugh Longbourne Callendar who held the chair of Macdonald Professor of physics and was coming to Cambridge.[19] Rutherford was accepted, which meant that in 1900 he could marry Mary Georgina Newton (1876–1954)[20][21] to whom he had become engaged before leaving New Zealand; they married at St Paul's Anglican Church, Papanui in Christchurch,[22][23] they had one daughter, Eileen Mary (1901–1930), who married the physicist Ralph Fowler. In 1901, Rutherford gained a DSc from the University of New Zealand.[18] In 1907, he returned to Britain to take the chair of physics at the Victoria University of Manchester.\n" +
                 "Later years and honours\n" +
                 "\n" +
                 "Rutherford was knighted in 1914.[24] During World War I, he worked on a top secret project to solve the practical problems of submarine detection by sonar.[25] In 1916, he was awarded the Hector Memorial Medal. In 1919, he returned to the Cavendish succeeding J. J. Thomson as the Cavendish professor and Director. Under him, Nobel Prizes were awarded to James Chadwick for discovering the neutron (in 1932), John Cockcroft and Ernest Walton for an experiment which was to be known as splitting the atom using a particle accelerator, and Edward Appleton for demonstrating the existence of the ionosphere. In 1925, Rutherford pushed calls to the New Zealand Government to support education and research, which led to the formation of the Department of Scientific and Industrial Research (DSIR) in the following year.[26] Between 1925 and 1930, he served as President of the Royal Society, and later as president of the Academic Assistance Council which helped almost 1,000 university refugees from Germany.[3] He was appointed to the Order of Merit in the 1925 New Year Honours[27] and raised to the peerage as Baron Rutherford of Nelson, of Cambridge in the County of Cambridge in 1931,[28] a title that became extinct upon his unexpected death in 1937. In 1933, Rutherford was one of the two inaugural recipients of the T. K. Sidey Medal, set up by the Royal Society of New Zealand as an award for outstanding scientific research.[29][30]\n" +
                 "Lord Rutherford's grave in Westminster Abbey\n" +
                 "\n" +
                 "For some time before his death, Rutherford had a small hernia, which he had neglected to have fixed, and it became strangulated, causing him to be violently ill. Despite an emergency operation in London, he died four days afterwards of what physicians termed \"intestinal paralysis\", at Cambridge.[31] After cremation at Golders Green Crematorium,[31] he was given the high honour of burial in Westminster Abbey, near Isaac Newton and other illustrious British scientists.[32]\n" +
                 "Scientific research\n" +
                 "\t\n" +
                 "This section needs additional citations for verification. Please help improve this article by adding citations to reliable sources. Unsourced material may be challenged and removed. (July 2019) (Learn how and when to remove this template message)\n" +
                 "Ernest Rutherford at McGill University in 1905\n" +
                 "\n" +
                 "At Cambridge, Rutherford started to work with J. J. Thomson on the conductive effects of X-rays on gases, work which led to the discovery of the electron which Thomson presented to the world in 1897. Hearing of Becquerel's experience with uranium, Rutherford started to explore its radioactivity, discovering two types that differed from X-rays in their penetrating power. Continuing his research in Canada, he coined the terms alpha ray and beta ray[33] in 1899 to describe the two distinct types of radiation. He then discovered that thorium gave off a gas which produced an emanation which was itself radioactive and would coat other substances.[34] He found that a sample of this radioactive material of any size invariably took the same amount of time for half the sample to decay – its \"half-life\" (11½ minutes in this case).\n" +
                 "\n" +
                 "From 1900 to 1903, he was joined at McGill by the young chemist Frederick Soddy (Nobel Prize in Chemistry, 1921) for whom he set the problem of identifying the thorium emanations. Once he had eliminated all the normal chemical reactions, Soddy suggested that it must be one of the inert gases, which they named thoron (later found to be an isotope of radon). They also found another type of thorium they called Thorium X, and kept on finding traces of helium. They also worked with samples of \"Uranium X\" from William Crookes and radium from Marie Curie.\n" +
                 "\n" +
                 "In 1903, they published their \"Law of Radioactive Change\", to account for all their experiments. Until then, atoms were assumed to be the indestructible basis of all matter and although Curie had suggested that radioactivity was an atomic phenomenon, the idea of the atoms of radioactive substances breaking up was a radically new idea. Rutherford and Soddy demonstrated that radioactivity involved the spontaneous disintegration of atoms into other, as yet, unidentified matter. The Nobel Prize in Chemistry 1908 was awarded to Ernest Rutherford \"for his investigations into the disintegration of the elements, and the chemistry of radioactive substances\".[35]\n" +
                 "\n" +
                 "In 1903, Rutherford considered a type of radiation discovered (but not named) by French chemist Paul Villard in 1900, as an emission from radium, and realised that this observation must represent something different from his own alpha and beta rays, due to its very much greater penetrating power. Rutherford therefore gave this third type of radiation the name of gamma ray. All three of Rutherford's terms are in standard use today – other types of radioactive decay have since been discovered, but Rutherford's three types are among the most common.\n" +
                 "\n" +
                 "In 1904, Rutherford suggested that radioactivity provides a source of energy sufficient to explain the existence of the Sun for the many millions of years required for the slow biological evolution on Earth proposed by biologists such as Charles Darwin. The physicist Lord Kelvin had argued earlier for a much younger Earth (see also William Thomson, 1st Baron Kelvin#Age of the Earth: geology) based on the insufficiency of known energy sources, but Rutherford pointed out at a lecture attended by Kelvin that radioactivity could solve this problem.[36]\n" +
                 "\n" +
                 "In Manchester, he continued to work with alpha radiation. In conjunction with Hans Geiger, he developed zinc sulfide scintillation screens and ionisation chambers to count alphas. By dividing the total charge they produced by the number counted, Rutherford decided that the charge on the alpha was two. In late 1907, Ernest Rutherford and Thomas Royds allowed alphas to penetrate a very thin window into an evacuated tube. As they sparked the tube into discharge, the spectrum obtained from it changed, as the alphas accumulated in the tube. Eventually, the clear spectrum of helium gas appeared, proving that alphas were at least ionised helium atoms, and probably helium nuclei.\n" +
                 "\n" +
                 "A long-standing myth existed, at least as early as 1948,[37][38] running at least to 2017, that Rutherford was the first scientist to observe and report an artificial transmutation of a stable element into another element: nitrogen into oxygen. It was thought by many people to be one of Rutherford's greatest accomplishments.[39][40] The New Zealand government even issued a commemorative stamp in the belief that the nitrogen-to-oxygen discovery belonged to Rutherford.[41] Beginning in 2017, many scientific institutions corrected their versions of this history to indicate that the discovery credit for the reaction belongs to Patrick Blackett.[42] Rutherford did detect the ejected proton in 1919 and interpreted it as evidence for disintegration of the nitrogen nucleus (to lighter nuclei). In 1925, Blackett showed that the actual product is oxygen and identified the true reaction as 14N + α → 17O + p. Rutherford therefore recognized \"that the nucleus may increase rather than diminish in mass as the result of collisions in which the proton is expelled\".[43]\n" +
                 "Gold foil experiment\n" +
                 "Top: Expected results: alpha particles passing through the plum pudding model of the atom undisturbed.\n" +
                 "Bottom: Observed results: a small portion of the particles were deflected, indicating a small, concentrated charge. Diagram is not to scale; in reality the nucleus is vastly smaller than the electron shell.\n" +
                 "\n" +
                 "Rutherford performed his most famous work after receiving the Nobel prize in 1908. Along with Hans Geiger and Ernest Marsden in 1909, he carried out the Geiger–Marsden experiment, which demonstrated the nuclear nature of atoms by deflecting alpha particles passing through a thin gold foil. Rutherford was inspired to ask Geiger and Marsden in this experiment to look for alpha particles with very high deflection angles, of a type not expected from any theory of matter at that time. Such deflections, though rare, were found, and proved to be a smooth but high-order function of the deflection angle. It was Rutherford's interpretation of this data that led him to formulate the Rutherford model of the atom in 1911 – that a very small charged[9] nucleus, containing much of the atom's mass, was orbited by low-mass electrons.\n" +
                 "\n" +
                 "In 1919–1920, Rutherford found that nitrogen and other light elements ejected a proton, which he called a \"hydrogen atom\", when hit with α (alpha) particles.[44] This result showed Rutherford that hydrogen nuclei were a part of nitrogen nuclei (and by inference, probably other nuclei as well). Such a construction had been suspected for many years on the basis of atomic weights which were whole numbers of that of hydrogen; see Prout's hypothesis. Hydrogen was known to be the lightest element, and its nuclei presumably the lightest nuclei. Now, because of all these considerations, Rutherford decided that a hydrogen nucleus was possibly a fundamental building block of all nuclei, and also possibly a new fundamental particle as well, since nothing was known from the nucleus that was lighter. Thus, confirming and extending the work of Wilhelm Wien who in 1898 discovered the proton in streams of ionized gas,[45] Rutherford postulated the hydrogen nucleus to be a new particle in 1920, which he dubbed the proton.\n" +
                 "\n" +
                 "In 1921, while working with Niels Bohr (who postulated that electrons moved in specific orbits), Rutherford theorized about the existence of neutrons, (which he had christened in his 1920 Bakerian Lecture), which could somehow compensate for the repelling effect of the positive charges of protons by causing an attractive nuclear force and thus keep the nuclei from flying apart from the repulsion between protons. The only alternative to neutrons was the existence of \"nuclear electrons\" which would counteract some of the proton charges in the nucleus, since by then it was known that nuclei had about twice the mass that could be accounted for if they were simply assembled from hydrogen nuclei (protons). But how these nuclear electrons could be trapped in the nucleus, was a mystery.\n" +
                 "\n" +
                 "Rutherford is widely quoted as saying, regarding the results of these experiments: \"It was quite the most incredible event that has ever happened to me in my life. It was almost as incredible as if you fired a 15-inch shell at a piece of tissue paper and it came back and hit you.\"[46]\n" +
                 "\n" +
                 "Rutherford's theory of neutrons was proved in 1932 by his associate James Chadwick, who recognized neutrons immediately when they were produced by other scientists and later himself, in bombarding beryllium with alpha particles. In 1935, Chadwick was awarded the Nobel Prize in Physics for this discovery.\n" +
                 "Legacy\n" +
                 "A plaque commemorating Rutherford's presence at the University of Manchester\n" +
                 "\n" +
                 "Rutherford is considered to have been among the greatest scientists in history. At the opening session of the 1938 Indian Science Congress, which Rutherford had been expected to preside over before his death, astrophysicist James Jeans spoke in his place and deemed him \"one of the greatest scientists of all time\", saying:\n" +
                 "\n" +
                 "    In his flair for the right line of approach to a problem, as well as in the simple directness of his methods of attack, [Rutherford] often reminds us of Faraday, but he had two great advantages which Faraday did not possess, first, exuberant bodily health and energy, and second, the opportunity and capacity to direct a band of enthusiastic co-workers. Great though Faraday's output of work was, it seems to me that to match Rutherford's work in quantity as well as in quality, we must go back to Newton. In some respects he was more fortunate than Newton. Rutherford was ever the happy warrior – happy in his work, happy in its outcome, and happy in its human contacts.[47]\n" +
                 "\n" +
                 "Nuclear physics\n" +
                 "nitrogen plasma\n" +
                 "\n" +
                 "Rutherford's research, and work done under him as laboratory director, established the nuclear structure of the atom and the essential nature of radioactive decay as a nuclear process. Patrick Blackett, a research fellow working under Rutherford, using natural alpha particles, demonstrated induced nuclear transmutation. Rutherford's team later, using protons from an accelerator, demonstrated artificially-induced nuclear reactions and transmutation. He is known as the father of nuclear physics. Rutherford died too early to see Leó Szilárd's idea of controlled nuclear chain reactions come into being. However, a speech of Rutherford's about his artificially-induced transmutation in lithium, printed in 12 September 1933 London paper The Times, was reported by Szilárd to have been his inspiration for thinking of the possibility of a controlled energy-producing nuclear chain reaction. Szilard had this idea while walking in London, on the same day.\n" +
                 "\n" +
                 "Rutherford's speech touched on the 1932 work of his students John Cockcroft and Ernest Walton in \"splitting\" lithium into alpha particles by bombardment with protons from a particle accelerator they had constructed. Rutherford realized that the energy released from the split lithium atoms was enormous, but he also realized that the energy needed for the accelerator, and its essential inefficiency in splitting atoms in this fashion, made the project an impossibility as a practical source of energy (accelerator-induced fission of light elements remains too inefficient to be used in this way, even today). Rutherford's speech in part, read:\n" +
                 "\n" +
                 "    We might in these processes obtain very much more energy than the proton supplied, but on the average we could not expect to obtain energy in this way. It was a very poor and inefficient way of producing energy, and anyone who looked for a source of power in the transformation of the atoms was talking moonshine. But the subject was scientifically interesting because it gave insight into the atoms.";

         text = text.replaceAll("\n", "");
         text = text.replaceAll("\"", " ");
         text = text.replaceAll("\\.", " ");
         text = text.replaceAll(",", " ");

         Set<String> phrases = Arrays.stream(text.split("\\s")).filter(s -> s.length() == 12).collect(Collectors.toSet());

//         String[] phrases = {
//                 //"Rutherford",
//                 //"rutherfordium"
//                 //"Spring Grove"
//                 //"_Rutherford_"
//                 //"30August1871"
//                 ""
//
//         };

         IvGenerator[] ivGenerators = {new NoIvGenerator(), new RandomIvGenerator()};

         for (String phrase : phrases) {
             for (String algo : algos) {
                 for (IvGenerator ivGenerator : ivGenerators) {
                     int i = 1000;
                     //for (int i = 1; i <= 1000; i++) {
                         try {
                             decrypt(phrase, algo, ivGenerator, i);
                             System.out.println("Success!: " + phrase + " " + algo);
                         } catch (IOException | EncryptionInitializationException | EncryptionOperationNotPossibleException e) {
                             System.out.println("Failed " + algo);
                         }
                         try {
                             decrypt(phrase.toLowerCase(Locale.ROOT), algo, ivGenerator, i);
                             System.out.println("Success!: " + phrase + " " + algo);
                         } catch (IOException | EncryptionInitializationException | EncryptionOperationNotPossibleException e) {
                             System.out.println("Failed " + algo);
                         }
                         try {
                             decrypt(phrase.toUpperCase(Locale.ROOT), algo, ivGenerator, i);
                             System.out.println("Success!: " + phrase + " " + algo);
                         } catch (IOException | EncryptionInitializationException | EncryptionOperationNotPossibleException e) {
                             System.out.println("Failed " + algo);
                         }
                     //}
                 }
             }
         }
     }

    private static void decrypt(String phrase, String cipher, IvGenerator ivGenerator, int iterations) throws IOException, EncryptionOperationNotPossibleException {

        StandardPBEStringEncryptor textEncryptor = new StandardPBEStringEncryptor();
        textEncryptor.setPassword(phrase);                        // we HAVE TO set a password
        textEncryptor.setAlgorithm(cipher);  // optionally set the algorithm
        textEncryptor.setIvGenerator(ivGenerator);      // for PBE-AES-based algorithms, the IV generator is MANDATORY
        textEncryptor.setSaltGenerator(new RandomSaltGenerator());
        textEncryptor.setKeyObtentionIterations(iterations);
        SimpleStringPBEConfig simpleStringPBEConfig = new SimpleStringPBEConfig();
        simpleStringPBEConfig.setPoolSize(8);
        simpleStringPBEConfig.setPassword(phrase);
        simpleStringPBEConfig.setAlgorithm(cipher);
        simpleStringPBEConfig.setIvGenerator(ivGenerator);
        simpleStringPBEConfig.setSaltGenerator(new RandomSaltGenerator());
        simpleStringPBEConfig.setKeyObtentionIterations(iterations);
        textEncryptor.setConfig(simpleStringPBEConfig);

        Charset charset = Charset.defaultCharset();

        FileChannel fc = FileChannel.open(Paths.get("/home/andreas/Desktop/cybersec2021/01/text2.txt"));
        MappedByteBuffer bb = fc.map(FileChannel.MapMode.READ_ONLY, 0, fc.size());
        String data =  charset.decode(bb).toString();
        data = data.replaceAll("\n","");


        String plaintext = textEncryptor.decrypt(data);
        //if (plaintext.toLowerCase().contains("flag")) {
            System.out.println(plaintext);
        //}
    }
}
