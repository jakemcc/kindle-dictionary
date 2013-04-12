(ns kindle-dictionary.core
  (:require [hiccup.core :refer [html]]
            [hiccup.page :refer [html4 include-css]]))

(defn define [{:keys [entry definition]}]
  [:div.definition
   [:idx:entry {:name "default"}
    [:h2 [:idx:orth entry]]
    definition]])

(def ^:dynamic sample-words
  [{:entry "zzzz" :definition "snoring sound"}
   {:entry "fighter"
    :definition "someone with a sword"}
   {:entry "chair"
    :definition "You sit in this"}
   {:entry "Gesserit-Guild"
    :definition (str "Secret guild " (rand-int 100))}])

(defn generate-document [words]
  (html4 [:head
          (include-css "style.css")
          [:meta {:http-equiv "content-type"
                  :content "text/html"}]]
         [:body
          (map define (sort-by :entry
                               (fn [x y] (.compareToIgnoreCase x y))
                               words))]))

(def dune-words
  [
   { :entry "Aba" :definition "A loose, usually black robe worn by Fremen women and Bene Gesserit sisters." }
   { :entry "Abomination" :definition "Bene Gesserit term for one who is pre-born and thus susceptible to being taken over by the ancestral personalities in Other Memory." }
   { :entry "Ajidamal (or Amal)" :definition "Disastrously-flawed synthetic melange created by the Tleilaxu Project Amal before the process of producing spice in axlotl tanks is perfected." }
   { :entry "Akarso" :definition "\"Plant native to Sikun (of 70 Ophiuchi A) characterized by almost oblong leaves. Its green and white stripes indicate the constant multiple, condition of parallel active and dormant chlorophyll regions.\"" }
   { :entry "Al-Lat" :definition "\"Mankind's original sun; by usage: any planet's primary.\"" }
   { :entry "Ampoliros" :definition "\"Legendary 'Flying Dutchman' of space;\" Lady Jessica refers to the pre-Guild legend in Dune: \"Like the men of the lost star-searcher, Ampoliros - sick at their guns - forever seeking, forever prepared and forever unready.\"" }
   { :entry "Amtal (or Amtal Rule)" :definition "\"Common rule on primitive worlds under which something is tested to determine its limits or defects. Commonly: testing to destruction.\"" }
   { :entry "Arafel" :definition "The \"cloud-darkness of holy judgment\"" }
   { :entry "Axlotl tank" :definition "Biological tank in which gholas and melange may be produced." }
   { :entry "Assassin's Handbook" :definition "\"Third-century compilation of poisons commonly used in a War of Assassins. Later expanded to include those deadly devices permitted under the Guild Peace and Great Convention.\"" }
   { :entry "Ayat" :definition "\"The signs of life. (See Burhan.)\"" }
   { :entry "Baliset" :definition "\"Nine-stringed musical instrument, lineal descendant of the zithra, tuned to the Chusuk scale and played by strumming. Favorite instrument of Imperial troubadours.\" In the miniseries Frank Herbert's Dune, the baliset resembles a renaissance-era lute, with the pegbox bent back almost 90 degrees." }
   { :entry "Baraka" :definition "\"A living holy man of magical powers.\"" }
   { :entry "Bashar" :definition "Military rank, slightly above a traditional Colonel and primarily used for military leader of a planetary subdistrict; alternately, Colonel Bashar, or Supreme Bashar for a military's most senior commander." }
   { :entry "Bene Gesserit" :definition "Secretive and powerful matriarchal order whose members possess extraordinary physical and mental powers." }
   { :entry "Bene Tleilax (or Tleilaxu)" :definition "Secretive and powerful patriarchal race known for their genetic manipulation technologies." }
   { :entry "Bi-la kaifa" :definition "A Fremen religious phrase meaning \"Amen,\" which translates literally to \"Nothing further need be explained.\"" }
   { :entry "Buddislam" :definition "a merger of the ancient religions of Buddhism and Islam." }
   { :entry "Burhan" :definition "\"The proofs of life. (Commonly: the ayat and burhan of life. See Ayat.)\"" }
   { :entry "Burseg" :definition "Military general." }
   { :entry "Butlerian Jihad" :definition "Mankind's \"crusade against computers, thinking machines, and conscious robots.\"" }
   { :entry "Caid" :definition "\"Sardaukar officer rank given to a military official whose duties call mostly for dealings with civilians; A military governorship over a full planetary district; Above the rank of Bashar but not equal to a Burseg.\"" }
   { :entry "Carryall" :definition "Aircraft used on Arrakis to \"transport large spice mining, hunting and refining equipment.\"" }
   { :entry "Chakobsa" :definition "Language of the Fremen of Arrakis." }
   { :entry "Chaumas" :definition "\"Poison in solid food as distinguished from poison administered in some other way.\"" }
   { :entry "Chaumurky" :definition "\"Poison administered in a drink.\"" }
   { :entry "CHOAM (Combine Honnete Ober Advancer Mercantiles)" :definition "the \"universal development corporation controlled by the Emperor and Great Houses with the Guild and Bene Gesserit as silent partners.\" This corporation essentially controls the economy of the known universe, with shares and directorships determining each House's income and financial leverage." }
   { :entry "Cogitor" :definition "One of several ancient philosophers whose brains are transplanted into fluid-filled canisters so that they can analyze the universe indefinitely." }
   { :entry "Cone of silence" :definition "Sound-deadening \"field of a distorter that limits the carrying power of the voice or any other vibrator by damping the vibrations with an image-vibration 180 degrees out of phase.\"" }
   { :entry "Coriolis storm" :definition "Sandstorms on Arrakis in which \"winds across the open flatlands are amplified by the planet's own revolutionary motion to reach speeds up to 700 kilometres per hour.\"" }
   { :entry "Crysknife" :definition "Weapon made from the tooth of a sandworm from Arrakis." }
   { :entry "Cutterray" :definition "\"Short-range version of a lasgun used mostly as a cutting tool and surgeon's scalpel.\"" }
   { :entry "Cymek" :definition "Type of cyborg; specifically, a human brain implanted into a large, weaponized machine body." }
   { :entry "D-wolves" :definition "Guardians of the Sareer on Arrakis in the time of Leto Atreides II; ferocious wolves descended from Gaze Hounds and ordinary wolves, \"noted for their keen eyesight.\"" }
   { :entry "Damper, Ixian" :definition "Portable device which hides words from anyone without the proper coded translator, and projects distortions that hide the precise movements of lips and the sounds of voices. It is described as a \"black disc\" buoyed midair by suspensors." }
   { :entry "Deathstill" :definition "Fremen device used to extract all moisture from a living or dead human or creature. This is traditionally done to reclaim precious water from the dead, who no longer require it; but in Children of Dune the device is used as a method of execution." }
   { :entry "Distrans" :definition "Steganographic device producing a \"temporary neural imprint on the nervous system of Chiroptera (bats) or birds. The creature's normal cry then carries the message imprint, which can be sorted from that carrier wave by another distrans.\"" }
   { :entry "Elacca drug" :definition "Narcotic produced from the \"blood-grained\" elacca wood of Ecaz; users' skin shows a characteristic carrot color. The drug's effect is to remove most of the will to self-preservation; commonly used to prepare slave gladiators for the ring." }
   { :entry "Face Dancers" :definition "Creatures created by the Bene Tleilax that are able to mimic other humans exactly and go undetected by all known means, except by Bene Gesserit Truthsayers." }
   { :entry "Famine Times" :definition "Years after the reign of Leto II, marked by chaos and famine on many worlds, that led to The Scattering." }
   { :entry "Faufreluches" :definition "\"The rigid rule of class distinction enforced by the Imperium. 'A place for every man and every man in his place.'\"" }
   { :entry "Fedaykin" :definition "(pronounced /fɨˈdaɪkɨn/ Personally trained by, and fiercely loyal to, Paul Atreides. See Fedayeen." }
   { :entry "Fish Speakers" :definition "All-female military force created by Leto Atreides II to enforce his rule over the known universe." }
   { :entry "Fogwood" :definition "Wood native to Ecaz, capable of being shaped by thought alone." }
   { :entry "Fremen" :definition "\"Native\" inhabitants of Arrakis." }
   { :entry "Fremkit" :definition "Fremen desert survival kit. Notable contents include an inflatable stilltent, a sand snorkel, a thumper, and maker hooks." }
   { :entry "Futar" :definition "Primitive and fierce humanoid creatures, a genetic mix of man and cat, artificially created out in the Scattering to hunt Honored Matres." }
   { :entry "Galach" :definition "Universal language of the Dune universe." }
   { :entry "Ghola" :definition "A type of Clone grown in an axlotl tank from genetic material retrieved from the cells of a deceased subject." }
   { :entry "Glowglobe" :definition "\"Suspensor-buoyed illuminating device, self-powered (usually by organic batteries).\"" }
   { :entry "Golden Path" :definition "Leto II's strategy to prevent humanity's destruction." }
   { :entry "Gom jabbar" :definition "\"Specific poison needle tipped with meta-cyanide used by Bene Gesserit Proctors in their death-alternative test of human awareness.\"" }
   { :entry "Great Convention" :definition "\"Universal truce enforced under the power balance maintained by the Guild, the Great Houses, and the Imperium. Its chief rule prohibits the use of atomic weapons against human targets.\"" }
   { :entry "Guild Navigator" :definition "Melange-mutated humans able to safely navigate interstellar space (using prescience) in ships called Heighliners." }
   { :entry "Handlers" :definition "Humanoid race who bred and trained Futars to hunt Honored Matres." }
   { :entry "Harvester (also Harvester Factory or Crawler)" :definition "\"Large (often 120 metres by 40 metres) spice mining machine ..." }
   { :entry "Heighliner" :definition "Enormous carrier starships used by the Spacing Guild for interstellar travel." }
   { :entry "High Council" :definition "\"The Landsraad inner circle empowered to act as supreme tribunal in House to House disputes.\"" }
   { :entry "Holtzman effect" :definition "Scientific phenomenon that makes (among other things) instantaneous space travel and defensive force shields possible." }
   { :entry "Honored Matres" :definition "Violent matriarchal order formed in The Scattering." }
   { :entry "Hunter-seeker" :definition "\"Ravening sliver of suspensor-buoyed metal guided as a weapon by a nearby control console; common assassination device.\"" }
   { :entry "Inkvine" :definition "\"Creeping plant native to Giedi Prime and frequently used as a whip in its slave pens. Victims are marked by beet-colored tattoos that cause residual pain for many years.\"" }
   { :entry "Ixian damper" :definition "Portable device which hides words from anyone without the proper coded translator, and projects distortions that hide the precise movements of lips and the sounds of voices. It is described as a \"black disc\" buoyed midair by suspensors." }
   { :entry "Ixian Probe" :definition "Device used to capture the thoughts of a person (living or dead) for analysis; can be blocked by the substance shere." }
   { :entry "Jihad, Butlerian" :definition "Mankind's \"crusade against computers, thinking machines, and conscious robots.\"" }
   { :entry "Judge of the Change" :definition "\"An official appointed by the Landsraad High Council and the Emperor to monitor a change of fief, a kanly negotiation, or formal battle in a War of Assassins. The Judge's arbitral authority may be challenged only before the High Council with the Emperor present.\"" }
   { :entry "Kanly" :definition "\"Formal feud or vendetta under the rules of the Great Convention carried on according to the strictest limitations.\"" }
   { :entry "Kralizec" :definition "Long-foretold \"Typhoon Struggle\" or final \"battle at the end of the universe.\"" }
   { :entry "Krimskell fiber/Krimskell rope" :definition "\"The 'claw fiber' woven from strands of the hufuf vine from Ecaz. Knots tied in krimskell will claw tighter and tighter to preset limits when the knot-lines are pulled.\"" }
   { :entry "Kwisatz Haderach" :definition "Bene Gesserit label applied to \"the unknown for which they sought a genetic solution: a male Bene Gesserit whose organic mental powers would bridge space and time.\"" }
   { :entry "Landsraad" :definition "The Assembly of all nobles in the Imperium." }
   { :entry "Lasgun" :definition "Handheld energy weapon, technically a \"continuous-wave laser projector;\" also lasegun." }
   { :entry "Laza tiger" :definition "A breed of tiger brought to Salusa Secundus \"almost eight thousand years\" before the events of Children of Dune. \"Genetic manipulation of the ancient Terran stock had erased some of the original tiger features and refined other elements. The fangs remained long. Their faces were wide, eyes alert and intelligent. The paws were enlarged to give them support on uneven terrain and their sheathed claws could extend some ten centimeters, sharpened at the ends into razor tips by abrasive compression of the sheath. Their coats were a flat and even tan which made them almost invisible against sand.\"" }
   { :entry "Little Maker" :definition "The \"half-plant-half-animal deep-sand vector of the Arrakis sandworm,\" whose \"excretions form the pre-spice mass.\"" }
   { :entry "Lost Tleilaxu" :definition "Offshoot race of the Bene Tleilax, formed in The Scattering." }
   { :entry "Levenbrech" :definition "A military rank that is roughly in between a sergeant and a lieutenant." }
   { :entry "Mahdi" :definition "\"In the Fremen messianic legend, 'The One Who Will Lead Us to Paradise;'\" applied to Paul Atreides by the Fremen when they determine that he is their prophesied messiah." }
   { :entry "Maker hooks" :definition "\"The hooks used for capturing, mounting, and steering a sandworm of Arrakis.\"" }
   { :entry "Maula pistol" :definition "\"Spring-loaded gun for firing poison darts; range about 40 metres.\"" }
   { :entry "Master of Assassins" :definition "A House functionary in charge of managing intelligence gathering, sabotage, propaganda and other \"soft\" methods of influence. They also manage the House's actions in a War of Assassins. The intellectually-taxing nature of the office means it is usually given to a Mentat." }
   { :entry "Melange" :definition "Highly-addictive drug essential to space travel, extended life, and therefore to the survival of the universe." }
   { :entry "Mentats" :definition "Individuals trained as \"human computers,\" their minds developed to staggering heights of cognitive and analytical ability." }
   { :entry "Missionaria Protectiva" :definition "An arm of the Bene Gesserit charged with spreading contrived myths, prophecies and superstition on primitive worlds so that the Bene Gesserit may later exploit those regions." }
   { :entry "Muad'Dib" :definition "\"The adapted kangaroo mouse of Arrakis, a creature associated in the Fremen earth-spirit mythology with a design visible on the planet's second moon. This creature is admired by Fremen for its ability to survive in the open desert.\" In Dune, Paul Atreides takes \"Muad'Dib\" as his Fremen name, which takes on greater significance when he is perceived as a messiah." }
   { :entry "Muadru" :definition "Ancient religion predating the Old Empire before the Time of the Titans." }
   { :entry "na-Baron" :definition "Noble title given to a Baron's heir-apparent." }
   { :entry "No-chamber" :definition "Construct that hides anything inside from prescient and ocular vision, as well as other methods of detection." }
   { :entry "No-ship" :definition "No-chamber in spaceship form, with enough limited prescience to be capable of interstellar travel without a Guild Navigator." }
   { :entry "Nullentropy" :definition "Technology akin to the science fiction concept of stasis, in which the natural processes of time, such as decomposition, are ceased. In this way, perishable matter such as food and even human cells may be stored for millennia and remain undamaged." }
   { :entry "Obliterators" :definition "Weapons of mass destruction stolen by Honored Matres from their \"outside enemy;\" they combust the atmosphere of a planet and subsequently its surface." }
   { :entry "Other Memory" :definition "The combined ego and memories of all female ancestors, which a Bene Gesserit may be trained to access." }
   { :entry "Ornithopter (or 'Thopter)" :definition "\"Aircraft capable of sustained wing-beat flight in the manner of birds;\"" }
   { :entry "Palm lock" :definition "\"Lock or seal which can be opened only by contact with the palm of the human hand to which it has been keyed.\"" }
   { :entry "Phibian" :definition "Primitive and amphibious humanoid creatures, a genetic mix of man and fish." }
   { :entry "Plasteel" :definition "Extremely tough form of steel, \"stabilized with stravidium fibers grown into its crystal structure.\"" }
   { :entry "Plaz (or windowplaz)" :definition "Synthetic glass, used for windows (especially in aircraft and spaceships) due to its superior strength." }
   { :entry "Poison snooper" :definition "\"Radiation analyzer within the olfactory spectrum and keyed to detect poisonous substances.\"" }
   { :entry "Powindah" :definition "Tleilaxu term for all outsiders, whom they consider \"unclean\" sinners and heretics." }
   { :entry "Prana-bindu" :definition "Training providing a Bene Gesserit with complete muscle control (Prana nervature relates to nervous system control)." }
   { :entry "Pre-spice mass" :definition "The \"stage of fungusoid wild growth achieved when water is flooded into the excretions of Little Makers. At this stage, the spice of Arrakis forms a characteristic 'blow,' exchanging the material from deep underground for the matter on the surface above it. This mass, after exposure to sun and air, becomes melange.\"" }
   { :entry "Probe, Ixian" :definition "Device used to capture the thoughts of a person (living or dead) for analysis; can be blocked by the substance shere." }
   { :entry "Probe, T" :definition "Device used to capture the thoughts of a person (living or dead) for analysis; unlike an Ixian Probe, it cannot be blocked by the substance shere." }
   { :entry "Pundi rice" :definition "\"A mutated rice whose grains, high in natural sugar, achieve lengths up to four centimeters; chief export of Caladan.\"" }
   { :entry "Qanat" :definition "\"Open canal for carrying irrigation water under controlled conditions through a desert.\"" }
   { :entry "Residual poison" :definition "\"Innovation attributed to the Mentat Piter De Vries whereby the body is impregnated with a substance for which repeated antidotes must be administered; withdrawal of the antidote at any time brings death.\"" }
   { :entry "Reverend Mother" :definition "Bene Gesserit who has survived a ritual wherein she consciously transforms a toxic dose of melange into a non-poisonous substance at the molecular level, thereby raising herself to a higher level of awareness and enabling her to access Other Memory." }
   { :entry "Salusan bull" :definition "Fierce and very aggressive creature with compound eyes and multiple horns, used in bullfighting on Caladan and originating from Salusa Secundus." }
   { :entry "Sandworm" :definition "Giant sand-dwelling creatures native to Arrakis. Called Shai-Hulud by the Fremen and worshipped as deities." }
   { :entry "Sapho" :definition "\"High-energy liquid extracted from barrier roots of Ecaz. Commonly used by Mentats who claim it amplifies mental powers. Users develop deep ruby stains on mouth and lips.\"" }
   { :entry "Sardaukar" :definition "Ferocious \"soldier-fanatics\" of the Padishah Emperor, later disbanded by Leto II." }
   { :entry "Sareer" :definition "Last desert of Arrakis in the time of Leto Atreides II, location of his Citadel; guarded by D-wolves." }
   { :entry "Sayyadina" :definition "Among the Fremen, the Sayyadina (\"Friend of God\" in Chakobsa) is a priestess who has not yet passed within to become a Reverend Mother. When a Sayyadina undergoes the spice agony, another is then consecrated into the Sayyadina to continue the line of succession." }
   { :entry "Scattering, The" :definition "Event after the reign of Leto II in which trillions of people left the settled worlds of the Old Empire, striking off into unknown space." }
   { :entry "Selamlik" :definition "\"Imperial audience chamber.\"" }
   { :entry "Semuta" :definition "\"Second narcotic derivative (by crystal extraction) from the burned residue of elacca wood" }
   { :entry "Shai-Hulud" :definition "Fremen name for the sandworms of Arrakis." }
   { :entry "Shere" :definition "Significant presence of this substance in the body will block the use of an Ixian Probe, but not a T-Probe from recovering memories." }
   { :entry "Shigawire" :definition "\"Metallic extrusion of a ground vine (Narvi narviium) grown only on Salusa Secundus and III Delta Kaising. It is noted for extreme tensile strength\" and is used as a recording medium as well as a garrote weapon." }
   { :entry "Sietch" :definition "Cave warren inhabited by a Fremen tribal community; in the Fremen language, \"Place of assembly in time of danger.\"" }
   { :entry "Sietch orgy (or sietch tau orgy)" :definition "Fremen ritual of unrestrained sexual indulgence which takes place after a Reverend Mother shares the changed Water of Life with her community." }
   { :entry "Siridar" :definition "A planetary governor." }
   { :entry "Slig" :definition "Livestock animal, hybrid of a large slug and a pig, considered a delicacy." }
   { :entry "Solido" :definition "\"Three-dimensional image from a solido projector using 360-degree reference signals imprinted on a shigawire reel.\"" }
   { :entry "Soostone" :definition "Valuable iridescent gem produced on Buzzell by the abraded carapaces of monoped sea creatures called Cholisters, much in the manner of pearls." }
   { :entry "Spacing Guild" :definition "Powerful organization with a monopoly on space travel and transport due to their Navigators." }
   { :entry "Spice agony" :definition "Fremen version of the ritual used by the Bene Gesserit to create Reverend Mothers, using the Water of Life instead of melange." }
   { :entry "Steersman" :definition "Title given to Guild Navigators." }
   { :entry "Stillsuit" :definition "\"Body-enclosing garment\" of Fremen design which performs the \"functions of heat dissipation and filtering bodily wastes,\" as well as retaining and reclaiming moisture." }
   { :entry "Stilltent" :definition "\"Small, scalable enclosure of micro-sandwich fabric designed to reclaim as potable water the ambient moisture discharged within it by the breath of its occupants.\"" }
   { :entry "Stone burner" :definition "Atomic weapon, the explosion and radiation of which can be precisely adjusted depending on the desired effect. A stone burner with sufficient fuel can burn through the crust of a planet to the mantle or core, potentially causing a release of energy destroying the surface of the planet on which the weapon is deployed. Stone burners emit \"J-Rays,\" a form of radiation that has an affinity for destroying the eyes of anyone surviving the initial radiation blast." }
   { :entry "Suk School" :definition "Prominent medical school whose doctors are the universe's most competent and trusted; those who have received the \"Suk Imperial Conditioning\" bear a diamond tattoo on their foreheads, wear their hair in a special silver ring, and are incapable of inflicting harm. However, the fallibility of Suk training is proven in Dune (1965) when Baron Vladimir Harkonnen and his twisted Mentat Piter De Vries manage to subvert this conditioning and coerce Suk Dr. Wellington Yueh into helping him in his attempt to destroy House Atreides." }
   { :entry "Suspensor" :definition "Any of a number of 'hovering' devices which utilize the \"secondary (low-drain) phase of a Holtzman field generator\" to nullify gravity \"within certain limits prescribed by relative mass and energy consumption.\"" }
   { :entry "T-Probe" :definition "Device used to capture the thoughts of a person (living or dead) for analysis. shere only prevents the T-Probe from recovering memories directly (as it does for the Ixian Probe) and does not impede any of the other features. The model created by the operation of this probe can be interrogated to give an idea of how the person would have reacted to a set of stimuli, possibly giving insight into a shere-loaded prisoner." }
   { :entry "Tachyon net (or tachyon web)" :definition "Technology (involving faster-than-light tachyon particles) used by Daniel and Marty in their attempt to track and capture the no-ship Ithaca." }
   { :entry "Thinking machines" :definition "Intelligent and sentient machines, created in the likeness of a human mind. Thinking machines were abolished in the Butlerian Jihad." }
   { :entry "'Thopter (Ornithopter)" :definition "\"Aircraft capable of sustained wing-beat flight in the manner of birds.\"" }
   { :entry "Thorse" :definition "A six-legged pack animal bred for its stability." }
   { :entry "Thumper" :definition "\"Short stake with a spring-driven clapper at one end\", placed in the sand to 'call' sandworms, who are attracted to vibration and sound." }
   { :entry "Titans" :definition "Ancient dictators whose brains were transplanted into fearsome, weaponized machine bodies to achieve immortality." }
   { :entry "Tleilaxu (or Bene Tleilax)" :definition "Secretive and powerful patriarchal race known for their genetic manipulation technologies." }
   { :entry "Truthsayer" :definition "Bene Gesserit Reverend Mother who is able to \"detect insincerity or falsehood.\", the ability to become a truthsayer is genetic, although some ability to detect falsehood is given to all Bene Gesserit through their training." }
   { :entry "Umma" :definition "\"One of the brotherhood of prophets; a term of scorn in the Imperium, meaning any 'wild' person given to fanatical prediction.\"" }
   { :entry "Usul" :definition "Fremen word, meaning \"The strength of the base of the pillar;\". This is Paul's secret \"sietch name\" (known only to his tribe) given to him upon his joining the Fremen." }
   { :entry "Verite" :definition "Will-destroying narcotic from Ecaz that \"renders a person incapable of falsehood.\"" }
   { :entry "Voice" :definition "Training that allows the Bene Gesserit \"to control others merely by selected tone shadings of the voice.\"" }
   { :entry "War of Assassins" :definition "Regulated form of warfare between noble houses, intended to \"reduce involvement of innocent bystanders.\" The rules require \"formal declarations of intent and restrict permissible weapons.\"" }
   { :entry "Water of Life" :definition "Toxic liquid exhalation of a drowning sandworm, used by Fremen Reverend Mothers in the spice agony." }
   { :entry "Weirding way" :definition "Specialized martial art component of Bene Gesserit prana-bindu." }
   { :entry "Whale fur" :definition "Valuable commodity noted to be the original source of House Harkonnen's wealth." }
   { :entry "Windowplaz (or simply plaz)" :definition "Synthetic glass, used for windows (especially in aircraft and spaceships) due to its superior strength." }
   { :entry "Windtrap" :definition "Type of air well \"placed in the path of a prevailing wind and capable of precipitating moisture from the air caught within it, usually by a sharp and distinct drop in temperature within the trap.\"" }
   { :entry "Yali" :definition "\"A Fremen's personal quarters within the sietch.\"" }
   { :entry "Zensunni" :definition "Ancient religious sect, ancestors of the Fremen. (See also Zen Buddhism and Sunni Islam.)" }
   ])
