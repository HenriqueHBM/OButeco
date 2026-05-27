package buteco.controller.usuarios;

public class UsuariosController {
//
//    private UsuariosView view;
//    private UsuarioRepository usuarioRepository;
//    private UsuarioService usuarioService;
//    private CargoRepository cargoRepository;
//    private Scanner sc;
//    private ErroEntrada erroEntrada;
//    private Usuario usuarioLogado;
//
//    public UsuariosController(
//            Scanner sc,
//            ErroEntrada erroEntrada,
//            CargoRepository cargoRepository,
//            UsuarioRepository usuarioRepository,
//            UsuarioService usuarioService,
//            Usuario usuarioLogado
//            )
//    {
//        this.sc = sc;
//        this.erroEntrada = erroEntrada;
//        this.usuarioRepository = usuarioRepository;
//        this.usuarioService = usuarioService;
//        this.view = new UsuariosView(sc, erroEntrada, usuarioRepository);
//        this.cargoRepository = cargoRepository;
//        this.usuarioLogado = usuarioLogado;
//    }
//
//    public void index(){
//        int opcao;
//
//        do {
//            opcao = view.exibirMenu();
//            switch (opcao) {
//                case 1 -> cadastrarUsuario(usuarioLogado);
//                case 2 -> editarUsuario(usuarioLogado);
//                case 3 -> deletarUsuario(usuarioLogado);
//                case 4 -> view.exibirUsuario();
//                case 0 -> System.out.println("Saindo...");
//                default -> System.out.println("VALOR INVALIDO");
//            }
//        }while (opcao != 0);
//    }
//
//    public Usuario login () {
//
//        while( usuarioLogado == null ) {
//
//            System.out.println("===== LOGIN ======");
//
//            String login = erroEntrada.trataEntradaString("Login: ");
//            String senha = erroEntrada.trataEntradaString("Senha: ");
//
//
//                usuarioLogado = usuarioService.login(login, senha);
//
//                if( usuarioLogado == null ) {
//                    System.out.println("Usuário ou Senha Inválidos!");
//                }
//        }
//
//        System.out.println("Bem-vindo " + usuarioLogado.getNome());
//        return usuarioLogado;
//    }
//
//    public void cadastrarUsuario(Usuario usuarioLogado) {
//
//        if(usuarioLogado.getCargo().getId() != 2){
//            System.out.println("Acesso negado!");
//            return;
//        }
//
//        String nome = erroEntrada.trataEntradaString("Nome: ");
//        String login = erroEntrada.trataEntradaString("Login: ");
//        String senha = erroEntrada.trataEntradaString("Senha: ");
//
//        Long idCargo = erroEntrada.trataEntradaLong("Id do Cargo: ");
//        Cargo cargo = cargoRepository.findById(idCargo);
//
//        if (cargo == null) {
//            System.out.println("Cargo não encontrado");
//            return;
//        }
//
//        usuarioService.cadastrar(nome, login, senha, cargo);
//
//        System.out.println("Usuário cadastrado com sucesso!");
//    }
//
//    public void editarUsuario(Usuario usuarioLogado) {
//
//        if(usuarioLogado.getCargo().getId() != 2){
//            System.out.println("Acesso negado!");
//            return;
//        }
//            view.exibirUsuario();
//
//        Long id = erroEntrada.trataEntradaLong("Id do Usuario (0 para voltar): ");
//
//        if( id == 0 ){
//            return;
//        }
//
//        Usuario usuario = usuarioRepository.findById(id);
//
//        if (usuario == null) {
//            System.out.println("Usuario não encontrado!");
//            return;
//        }
//
//        String nome = erroEntrada.trataEntradaString("Novo nome: ");
//        String login = erroEntrada.trataEntradaString("Novo login: ");
//        String senha = erroEntrada.trataEntradaString("Nova senha: ");
//
//        usuario.setNome(nome);
//        usuario.setLogin(login);
//        usuario.setSenha(senha);
//
//        usuarioRepository.update(usuario);
//
//        System.out.println("Usuario atualizado com sucesso!");
//
//    }
//
//    public void deletarUsuario(Usuario usuarioLogado) {
//
//        if(usuarioLogado.getCargo().getId() != 2){
//            System.out.println("Acesso negado!");
//            return;
//        }
//         view.exibirUsuario();
//
//        Long id = erroEntrada.trataEntradaLong("Id do Usuario (0 para voltar): ");
//
//        if( id == 0 ){
//            return;
//        }
//
//        Usuario usuario = usuarioRepository.findById(id);
//
//        if (usuario == null) {
//            System.out.println("Usuario não encontrado!");
//            return;
//        }
//
//        usuarioRepository.delete(usuario);
//        System.out.println("Usuario deletado com sucesso!");
//    }
//
//    public void setUsuarioLogado(Usuario usuarioLogado) {
//        this.usuarioLogado = usuarioLogado;
//    }

}
