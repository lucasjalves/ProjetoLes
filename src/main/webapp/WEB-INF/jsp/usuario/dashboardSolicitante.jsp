<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<!DOCTYPE html>
<html>
<head>
<jsp:include page="../header.jsp"></jsp:include>
<title>Painel Principal</title>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" type="image/png" sizes="16x16" href="../plugins/images/favicon.png">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<style>
@import url(https://fonts.googleapis.com/css?family=Roboto:300);

body {
  background: #76b852; /* fallback for old browsers */
  background: -webkit-linear-gradient(right, #76b852, #8DC26F);
  background: -moz-linear-gradient(right, #76b852, #8DC26F);
  background: -o-linear-gradient(right, #76b852, #8DC26F);
  background: linear-gradient(to left, #76b852, #8DC26F);
  font-family: "Roboto", sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;      
}
/*font Awesome http://fontawesome.io*/
@import url(//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css);
/*Comment List styles*/
.comment-list .row {
  margin-bottom: 0px;
}
.comment-list .panel .panel-heading {
  padding: 4px 15px;
  position: absolute;
  border:none;
  /*Panel-heading border radius*/
  border-top-right-radius:0px;
  top: 1px;
}
.comment-list .panel .panel-heading.right {
  border-right-width: 0px;
  /*Panel-heading border radius*/
  border-top-left-radius:0px;
  right: 16px;
}
.comment-list .panel .panel-heading .panel-body {
  padding-top: 6px;
}
.comment-list figcaption {
  /*For wrapping text in thumbnail*/
  word-wrap: break-word;
}
/* Portrait tablets and medium desktops */
@media (min-width: 768px) {
  .comment-list .arrow:after, .comment-list .arrow:before {
    content: "";
    position: absolute;
    width: 0;
    height: 0;
    border-style: solid;
    border-color: transparent;
  }
  .comment-list .panel.arrow.left:after, .comment-list .panel.arrow.left:before {
    border-left: 0;
  }
  /*****Left Arrow*****/
  /*Outline effect style*/
  .comment-list .panel.arrow.left:before {
    left: 0px;
    top: 30px;
    /*Use boarder color of panel*/
    border-right-color: inherit;
    border-width: 16px;
  }
  /*Background color effect*/
  .comment-list .panel.arrow.left:after {
    left: 1px;
    top: 31px;
    /*Change for different outline color*/
    border-right-color: #FFFFFF;
    border-width: 15px;
  }
  /*****Right Arrow*****/
  /*Outline effect style*/
  .comment-list .panel.arrow.right:before {
    right: -16px;
    top: 30px;
    /*Use boarder color of panel*/
    border-left-color: inherit;
    border-width: 16px;
  }
  /*Background color effect*/
  .comment-list .panel.arrow.right:after {
    right: -14px;
    top: 31px;
    /*Change for different outline color*/
    border-left-color: #FFFFFF;
    border-width: 15px;
  }
}
.comment-list .comment-post {
  margin-top: 6px;
}
</style>

<body>


    <div id="page-wrapper">
            </div>
            <!-- /.row -->
            <!--row -->
            <div class="row">
                <div class="col-sm-12">
                    <div class="white-box">
                        <h3 class="box-title">Chamados Atuais
                            <div class="col-md-2 col-sm-4 col-xs-12 pull-right">
                                <select class="form-control pull-right row b-none">
                                    <option>Março 2018</option>
                                    <option>Abril 2018</option>
                                    <option>Maio 2018</option>
                                    <option>Junho 2018</option>
                                    <option>Jullho 2018</option>
                                </select>
                            </div>
                        </h3>
                        <div class="table-responsive">
                            <table class="table ">
                                <thead>
                                    <tr>
                                        <th>TIPO</th>
                                        <th>RESUMO</th>
                                        <th>PRIORIDADE</th>
                                        <th>STATUS</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td class="txt-oflo">Tecnico</td>
                                        <td>Troca de perifericos</td>
                                        <td class="txt-oflo">Alta</td>
                                        <td class="txt-oflo">Resolvido</td>                                        
                                    </tr>
                                    <tr>
                                        <td class="txt-oflo">Serviço</td>
                                        <td>Instalar Antivirus</td>
                                        <td class="txt-oflo">baixa</td> 
                                        <td class="txt-oflo">Cancelado</td>                                       
                                    </tr>
                                    <tr>
                                        <td class="txt-oflo">Serviço</td>
                                        <td>Liberar Admin</td>
                                        <td class="txt-oflo">media</td>  
                                        <td class="txt-oflo">Em andamanto</td>                                      
                                    </tr>
                                    <tr>
                                        <td class="txt-oflo">Tecnico</td>
                                        <td>Troca de perifericos</td>
                                        <td class="txt-oflo">Alta</td> 
                                        <td class="txt-oflo">Em andamanto</td>                                       
                                    </tr>
                                    
                                </tbody>
                            </table> </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- /.container-fluid -->
      
    </div>
    <!-- /#page-wrapper -->
</div>
<div class="container" style="margin-left: 3px">
    <div class="row">
      <div class="col-md-8">
        <h2 class="page-header">Fluxo de chamados</h2>
          <section class="comment-list">
            <!-- First Comment -->
            <article class="row">
              <div class="col-md-2 col-sm-2 hidden-xs">
                <figure class="thumbnail">
                  <img class="img-responsive" src="http://www.tangoflooring.ca/wp-content/uploads/2015/07/user-avatar-placeholder.png" />
                  <figcaption class="text-center">username</figcaption>
                </figure>
              </div>
              <div class="col-md-10 col-sm-10">
                <div class="panel panel-default arrow left">
                  <div class="panel-body">
                    <header class="text-left">
                      <div class="comment-user"><i class="fa fa-user"></i> <a href>Troca de teclado</a></div>
                      <time class="comment-date" datetime="16-12-2014 01:05"><i class="fa fa-clock-o"></i> Dec 16, 2014</time>
                    </header>
                    <div class="comment-post">
                      <p>
                        O status do chamado foi trocado e está sendo processado
                      </p>
                    </div>
                  </div>
                </div>
              </div>
            </article>
            <!-- Second Comment Reply -->
            <!-- First Comment -->
            <!-- First Comment -->
            <article class="row">
                <div class="col-md-2 col-sm-2 hidden-xs">
                  <figure class="thumbnail">
                    <img class="img-responsive" src="http://www.tangoflooring.ca/wp-content/uploads/2015/07/user-avatar-placeholder.png" />
                    <figcaption class="text-center">username</figcaption>
                  </figure>
                </div>
                <div class="col-md-10 col-sm-10">
                  <div class="panel panel-default arrow left">
                    <div class="panel-body">
                      <header class="text-left">
                        <div class="comment-user"><i class="fa fa-user"></i> <a href>Troca de teclado</a></div>
                        <time class="comment-date" datetime="16-12-2014 01:05"><i class="fa fa-clock-o"></i> Dec 16, 2014</time>
                      </header>
                      <div class="comment-post">
                        <p>
                          O status do chamado foi trocado e está sendo processado
                        </p>
                      </div>
                    </div>
                  </div>
                </div>
              </article>
          <footer class="footer text-center"> 2018 &copy; Suport Plus </footer>
      </div>
    </div>
  </div>
  
<!-- /#wrapper -->
</body>
</html>